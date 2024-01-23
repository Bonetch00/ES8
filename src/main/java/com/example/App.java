package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {

    private static void sendBinaryFile(Socket socket, String path){

        if(path.endsWith("/")){
            path = path + "index.html";
        }
        if(path.equals("/classe.json")){
            creazioneClasse();
        }

        try{

            File file = new File("./root" + path);
            InputStream in = new FileInputStream(file);
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeBytes("HTTP/1.1 200 OK\n" );
            out.writeBytes("Content-Length: " + file.length()+ "\n");
            out.writeBytes("Server: Java HTTP Server from Taiti: 1.0\n");
            out.writeBytes("Date: " + new Date(0)+ "\n");
            out.writeBytes("Content-Type: " + getContentType(path) + "\n");
            out.writeBytes("\n");

            byte[] buf = new byte[8192];
            int n;
            while((n = in.read(buf)) != -1){
                out.write(buf, 0, n);
                System.out.println(buf);             
            } 
            out.close();
            in.close();
        }catch(FileNotFoundException ntF){
            try{
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                if(getContentType(path).equals("text/plain charset=utf-8\n")){
                    out.writeBytes("HTTP/1.1 301 Move Permanently\n" );
                    out.writeBytes("location: " + path+"/");
                }
                else
                    out.writeBytes("HTTP/1.1 404 not found");

            }catch(IOException e){
                System.out.println("IOexception");
            }
        }catch(IOException e){
            System.out.println("IOexception");
        }
           
    }

    private static String getContentType(String path) {
        String type = "text/plain charset=utf-8 \n";
        try {
            type=path.split("\\.")[1];
            System.out.println("-----------------------------"+type);
            switch (type) {
                case "html":
                    type = "text/html;" + "charset=utf-8";
                    break;
                case "jpg":
                case "png":
                case "jpeg":
                case "webp":
                    type = "image/" + type;
                    break;
                case "ico":
                    type = "favicon/ico";
                    break;
                case "css":
                    type = "text/html";
                    break;
                case "js":
                    type = "application/js";
                    break;
                case "json":
                    type="application/json";
                    break;
                default:
                    type = "text/plain";
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            // TODO: handle exception
            System.out.println(type);
        }
        return type;
    }

    private static void creazioneClasse(){
        Classe c= new Classe("5dia","tw_11");

        Alunno a1= new Alunno("Ammaar","Yasser","24/01/06");
        Alunno a2= new Alunno("Ardi","Ndreu","14/05/05");
        Alunno a3= new Alunno("Samuele","Taiti","27/06/05");

        c.add(a1);
        c.add(a2);
        c.add(a3);
        System.out.println("Classe creata");

        ObjectMapper objectMapper= new ObjectMapper();
        try {
            objectMapper.writeValue(new File("root/classe.json"), c);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }


    }
    public static void main( String[] args )
    {
        
        try {
            ServerSocket server = new ServerSocket(8080);
            while(true){
                Socket client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());

                String richiesta = "";

                richiesta = in.readLine();
                System.out.println(richiesta);
                String[] riga = richiesta.split(" ");
                String path = riga[1];
                
                
                
                System.out.println("--" + path + "--");

                do{
                    richiesta = in.readLine();
                    System.out.println(richiesta);
                    if(richiesta.isEmpty() || richiesta.equals(null)) break;
                }while(true);

                sendBinaryFile(client, path);
                
                out.flush();
                client.close();
            }          

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}