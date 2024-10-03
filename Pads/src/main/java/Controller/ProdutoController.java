package Controller;

import Model.Produto;
import Repository.ProdutoDAO;


public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        produtoDAO = new ProdutoDAO();
    }

    public void criarProduto(String nome, double valor) {
        if(valor <= 0){
            throw new IllegalArgumentException("Valor do produto deve ser maior que zero");
        }
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
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
