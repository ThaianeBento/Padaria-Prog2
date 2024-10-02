package Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    private String cpfCliente;
    private String metodoPagamento;

    // Relacionamento com produtos
    @ManyToMany
    @JoinTable(
            name = "venda_produto",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    public Venda(String cpf) {
        this.cpfCliente = cpf;
        this.produtos = new ArrayList<>();
    }

    public Venda() {
        this.produtos = new ArrayList<>();
    }

    //  add produto na venda
    public void addProduto(Produto p) {
        this.produtos.add(p);
    }

    // remover produto da venda
    public void removeProduto(Produto p) {
        this.produtos.remove(p);
    }

    public String escolhaPagamento (String metodoPagamento) {
        switch (metodoPagamento){
            case "1":
                metodoPagamento = "Cartão de crédito";
                break;
            case "2":
                metodoPagamento = "Cartão de débito";
                break;
            case "3":
                metodoPagamento = "Dinheiro";
                break;
        }
        return metodoPagamento;
    }

    // Imprimir nota fiscal
    public void imprimirNotaFiscal() {
        System.out.println("Nota Fiscal da Venda:");
        System.out.println("Cliente (CPF): " + this.cpfCliente);
        System.out.println("Método de pagamento: " + this.metodoPagamento);
        System.out.println("Produtos Comprados:");
        for (Produto p : this.produtos) {
            System.out.println(p.toString() + "\n");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
