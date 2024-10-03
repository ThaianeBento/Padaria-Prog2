package Controller;

import Model.Produto;
import Repository.ProdutoDAO;


public class produtoController {

    private ProdutoDAO produtoDAO;

    public produtoController() {
        produtoDAO = new ProdutoDAO();
    }

    public void criarProduto(String nome, double valor) {
        Produto produto = new Produto(nome, valor);
        produtoDAO.create(produto);
    }

    public Produto buscarProdutoPorNome(String nome) {
        return produtoDAO.readByNome(nome);
    }

    public void excluirProduto(String nome) {
        Produto produto = produtoDAO.readByNome(nome);
        if (produto != null) {
            produtoDAO.removeByNome(nome);
        }
    }
    
}
