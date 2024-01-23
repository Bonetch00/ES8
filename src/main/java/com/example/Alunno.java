package com.example;

import java.security.PublicKey;

public class Alunno {
    private String nome;
    private String cognome;
    private String dataDiNascita;
    
    public Alunno(String nome, String cognome,String dataDiNascita){
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita=dataDiNascita;
    }
    public Alunno (){}
    

    public String toString(){
        return "nome: " + nome + "\tcognome: "+ cognome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getDataDiNascita() {
        return dataDiNascita;
    }
    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
}

