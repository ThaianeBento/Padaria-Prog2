package Model;

public class Atendente extends Usuario{
    private String senha;

    public Atendente(String senha, String CPF, String nome) {
        super(CPF, nome);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }// criar regra de segurança

    public Cliente criarCliente(String CPF, String nome){

        return new Cliente(CPF,nome);
    }

    public boolean excluirCliente (String CPF){

        //preciso do BD
        return false;
    }

    public Venda registrarVenda (String CPF) {
        return new Venda(CPF);
    }

    public void solicitarCupom (Venda venda) {
        venda.imprimirNotaFiscal();
    }

    public int visualizarPontuacao (Cliente cliente) {
        return 0;
    }

}
