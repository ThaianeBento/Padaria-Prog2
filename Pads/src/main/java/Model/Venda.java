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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    private String metodoPagamento;

    private Double valorTotal;

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
                this.metodoPagamento = "Pix";
                break;
            case "2":
                this.metodoPagamento = "Cartão";
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

    public String imprimirNotaFiscal() {
        StringBuilder message = new StringBuilder();
        message.append("Nota Fiscal da Venda:\n");
        message.append("Cliente (CPF): ").append(this.cliente.getCPF()).append("\n");
        message.append("Método de pagamento: ").append(this.metodoPagamento).append("\n");
        message.append("Produtos Comprados: \n");
        for (Produto p : this.produtos) {
            message.append(p.toString()).append("\n");
        }
        message.append("Valor total: ").append(this.valorTotal);
        return message.toString();
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
