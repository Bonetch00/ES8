package com.example;

import java.util.ArrayList;

public class Classe {
    private ArrayList<Alunno> alunni;
    private String classe;
    private String aula;

    public Classe(String classe,String aula){
        alunni = new ArrayList();
        this.classe = classe;
        this.aula=aula;
    }
    public Classe(){}


    public void add(Alunno a){
         alunni.add(a);
    }

    public boolean remove(Alunno a){
        return alunni.remove(a);
    }

    public String toString(){
        String s = "";
        for (Alunno alunno : alunni) {
            s += alunno.toString() + "\n";
        }
        return s;
    }
    public ArrayList<Alunno> getAlunni() {
        return alunni;
    }
    public void setAlunni(ArrayList<Alunno> alunni) {
        this.alunni = alunni;
    }
    public String getClasse() {
        return classe;
    }
    public void setClasse(String classe) {
        this.classe = classe;
    }
    public String getAula() {
        return aula;
    }
    public void setAula(String aula) {
        this.aula = aula;
    }

    
}