package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public abstract class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    
    protected String CPF;
    protected String nome;
    
   public Usuario(String CPF, String nome){
       this.CPF = CPF;
       this.nome = nome;       
   }

    public long getId() {
        return id;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    } //Fazer if verificador

    public String getNome() {
        return nome;
    } 

    public void setNome(String nome) {
        this.nome = nome;
    }
      
   public void logout(){
       //voltar para tela de login
   }
   
}
