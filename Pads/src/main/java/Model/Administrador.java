package Model;

public class Administrador extends Atendente{

    public Administrador(String CPF, String nome, String senha) {super(CPF, nome, senha);}

    public Atendente criarAtendente(String CPF, String nome, String senha){
        return new Atendente(CPF,nome,senha);
    }

    public boolean excluirAtendente(String CPF){
        //BD
        return false;
    }

    public Produto adicionarProduto(String nome, double valor) {
        return new Produto(nome,valor);
    }

    public boolean excluirProduto(String nome){
        //BD
        return false;
    }

}
