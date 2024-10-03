package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import Repository.ClienteDAO;
import Repository.VendaDAO;
import java.util.List;

@Entity
public class Atendente extends Usuario {

    @Column
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

    public Cliente criarCliente(String CPF, String nome) {
        Cliente cliente = new Cliente(CPF, nome);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.create(cliente);
        return cliente;
    }

    public boolean excluirCliente(String CPF) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.read(CPF);
        if (cliente != null) {
            clienteDAO.remove(CPF);
            return true;
        }
        return false;
    }

    public Venda registrarVenda(Cliente cliente, List<Produto> produtos, String metodoPagamento) {
        Venda venda = new Venda(cliente);
        for (Produto produto : produtos) {
            venda.addProduto(produto);
        }
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.create(produtos, cliente, metodoPagamento);
        return venda;
    }

    public void solicitarCupom(Venda venda) {
        venda.imprimirNotaFiscal();
    }

    public int visualizarPontuacao(Cliente cliente) {
        return (int) cliente.getPontos();
    }
}
