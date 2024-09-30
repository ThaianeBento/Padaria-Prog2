package org.example.Model;


 
public class Atendente extends Usuario{
    private String senha;
    
    public Atendente(String CPF, String nome) {
        super(CPF, nome);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }// criar regra de segurança
    
    public Cliente criarCliente(){
        
        return null;
    }
    
}
