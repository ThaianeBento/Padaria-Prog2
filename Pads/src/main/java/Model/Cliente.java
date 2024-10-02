package Model;


public class Cliente extends Usuario{

    private double pontos;

    public Cliente(String CPF, String nome) {
        super(CPF, nome);
    }

    @Override
    public String getCPF() {
        return CPF;
    }

    @Override
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }



    public double getPontos() {
        return pontos;
    }

    public double addPontos(double valor){
        return this.pontos += valor;
    }

    private void setPontos(double pontos) {
        this.pontos = pontos;
    }


}
