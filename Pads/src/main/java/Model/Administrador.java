package Model;

import jakarta.persistence.Entity;
import Model.Atendente;

@Entity
public class Administrador extends Atendente{

    public Administrador(String CPF, String nome, String senha) {super(CPF, nome, senha);}

    public Atendente criarAtendente(String CPF, String nome, String senha){
        return Atendente;
    }

    public boolean excluirAtendente(String CPF){
        if () {
            return true;
        } else
            return false;
    }

    public Produto adicionarProduto(String nome, String valor) {
        return Produto;
    }

    public boolean excluirProduto(String nome){
        if () {
            return true;
        } else
            return false;
    }
}
