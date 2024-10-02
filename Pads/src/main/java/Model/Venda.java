package Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Relacionamento com Cliente em vez de usar cpfCliente diretamente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private String metodoPagamento;

    // Relacionamento com produtos
    @ManyToMany
    @JoinTable(
            name = "venda_produto",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos = new ArrayList<>();

    
    public Venda() {}

    public Venda(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void addProduto(Produto p) {
        this.produtos.add(p);
    }

    public void removeProduto(Produto p) {
        this.produtos.remove(p);
    }

    public void setMetodoPagamento(String metodoPagamento) {
        switch (metodoPagamento) {
            case "1":
                this.metodoPagamento = "Cartão de crédito";
                break;
            case "2":
                this.metodoPagamento = "Cartão de débito";
                break;
            case "3":
                this.metodoPagamento = "Dinheiro";
                break;
            default:
                throw new IllegalArgumentException("Método de pagamento inválido.");
        }
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void imprimirNotaFiscal() {
        System.out.println("Nota Fiscal da Venda:");
        System.out.println("Cliente (CPF): " + this.cliente.getCPF());
        System.out.println("Método de pagamento: " + this.metodoPagamento);
        System.out.println("Produtos Comprados:");
        for (Produto p : this.produtos) {
            System.out.println(p.toString() + "\n");
        }
    }

    public void finalizarVenda() {
        for (Produto p : this.produtos) {
            cliente.addPontos(p.getValor());
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
