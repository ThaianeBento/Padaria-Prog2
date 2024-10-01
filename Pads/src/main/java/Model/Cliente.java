package Model;


public class Cliente extends Usuario{

    private int pontos;

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



    public int getPontos() {
        return pontos;
    }

    private void setPontos(int pontos) {
        this.pontos = pontos;
    }


}
