package org.example.Model;


public class Cliente extends Usuario{
    
    private int pontos;
    
    public Cliente(String CPF, String nome) {
        super(CPF, nome);
    }

    public int getPontos() {
        return pontos;
    }

    private void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    
}
