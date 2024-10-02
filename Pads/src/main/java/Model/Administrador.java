package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Administrador extends Atendente {

    
    public Administrador() {}

    
    public Administrador(String CPF, String nome, String senha) {
        super(senha, CPF, nome);
    }

    // Método para criar um atendente e persistir no banco de dados
    public Atendente criarAtendente(String cpf, String nome, String senha) {
        Atendente atendente = new Atendente(senha, cpf, nome);
        // Aqui, você chamaria o AtendenteDAO para persistir o atendente no banco de dados
        // Exemplo: atendenteDAO.create(atendente);
        return atendente;
    }

    // Método para excluir um atendente (precisa do DAO para interagir com o banco de dados)
    public boolean excluirAtendente(String CPF) {
        // Aqui você chamaria o DAO para remover o atendente do banco de dados
        // Exemplo: return atendenteDAO.removeByCPF(CPF);
        return false;  // Retornar false por padrão até que a lógica de exclusão seja implementada
    }

    // Adicionar um novo produto
    public Produto adicionarProduto(String nome, double valor) {
        Produto produto = new Produto(nome, valor);
        // Aqui você chamaria o ProdutoDAO para persistir o produto no banco de dados
        // Exemplo: produtoDAO.create(produto);
        return produto;
    }

    // Método para excluir um produto pelo nome (precisa de DAO para o banco de dados)
    public boolean excluirProduto(String nome) {
        // Aqui você chamaria o ProdutoDAO para remover o produto do banco de dados
        // Exemplo: return produtoDAO.removeByName(nome);
        return false;  // Retornar false por padrão até que a lógica de exclusão seja implementada
    }
}

