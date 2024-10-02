package Model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(nullable = false, unique = true)
    protected String cpf;

    @Column(nullable = false)
    protected String nome;

    
    public Usuario() {}

    
    public Usuario(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

   
    public long getId() {
        return id;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        if (cpf.matches("\\d{11}")) { 
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return cpf.equals(usuario.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
