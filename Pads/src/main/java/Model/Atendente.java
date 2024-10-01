package Model;

import jakarta.persistence.Entity;


 @Entity
public class Atendente extends Usuario{
    private String senha;
    
    public Atendente(String CPF, String nome, String senha) {
        super(CPF, nome);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }// criar regra de segurança
    
    public Cliente criarCliente(String CPF, String nome){
        
        return Cliente;
    }

    public boolean excluirCliente (String CPF){
        if () {
            return true;
        } else
        return false;
    }

    public Venda registrarVenda (Cliente cliente) {
        return Venda;
    }

    public String solicitarCupom (Venda venda) {
        return "";
    }

    public int visualizarPontuacao (Cliente cliente) {
        return 0;
    }
    
}
