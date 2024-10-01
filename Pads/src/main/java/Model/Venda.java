package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    private String cpfCliente;
    private ArrayList<Produto> produtos;

    public Venda(String cpf) {
        this.cpfCliente = cpf;
        this.produtos = new ArrayList<>();
    }
    public void addProduto(Produto p) {
        this.produtos.add(p);
    }

    public void removeProduto (Produto p) {
        this.produtos.remove(p);
    }

    public void imprimirNotaFiscal(){
        for (Produto p : this.produtos) {
            String texto = p.toString();
            System.out.println(texto + "\n");
        }
    }
}
