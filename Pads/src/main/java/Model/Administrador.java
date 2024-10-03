package Model;

import Repository.AtendenteDAO;
import Repository.ProdutoDAO;
import jakarta.persistence.Entity;

@Entity
public class Administrador extends Atendente {

    public Administrador() {}

    public Administrador(String CPF, String nome, String senha) {
        super(senha, CPF, nome);
    }

    public Atendente criarAtendente(String cpf, String nome, String senha) {
        Atendente atendente = new Atendente(senha, cpf, nome);
        AtendenteDAO atendenteDAO = new AtendenteDAO();
        atendenteDAO.create(atendente);
        return atendente;
    }

    public boolean excluirAtendente(String CPF) {
        AtendenteDAO atendenteDAO = new AtendenteDAO();
        Atendente atendente = atendenteDAO.readByCPF(CPF);
        if (atendente != null) {
            atendenteDAO.remove(CPF);
            return true;
        }
        return false;
    }

    public Produto adicionarProduto(String nome, double valor) {
        Produto produto = new Produto(nome, valor);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.create(produto);
        return produto;
    }

    public boolean excluirProduto(String nome) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.readByNome(nome);
        if (produto != null) {
            produtoDAO.removeByNome(nome);
            return true;
        }
        return false;
    }
}
