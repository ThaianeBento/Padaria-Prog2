package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Atendente extends Usuario {

    @Column(nullable = false)
    private String senha;

    public Atendente() {}
  
    public Atendente(String senha, String cpf, String nome) {
        super(cpf, nome);
        this.senha = senha;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha.length() >= 8) {
            this.senha = senha;
        } else {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }
    }

    // Método para criar cliente
    public Cliente criarCliente(String CPF, String nome) {
        // Aqui você poderia adicionar lógica para persistir o cliente no banco de dados
        return new Cliente(CPF, nome);
    }

    // Método para excluir cliente (precisa de DAO para acessar o BD)
    public boolean excluirCliente(String CPF) {
        
        return false;
    }

    
    public Venda registrarVenda(String CPF) {
        
        return new Venda(CPF);
    }

    public void solicitarCupom(Venda venda) {
        venda.imprimirNotaFiscal();
    }

    
    public int visualizarPontuacao(Cliente cliente) {     
        return (int) cliente.getPontos();
    }
}

