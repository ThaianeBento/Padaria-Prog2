package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Cliente extends Usuario {

    @Column
    private double pontos;

    public Cliente() {}

     public Cliente(String cpf, String nome) {
        super(cpf, nome);
        this.pontos = 0;  
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }

    public double addPontos(double valor) {
        return this.pontos += valor;
    }
}
