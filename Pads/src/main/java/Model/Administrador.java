package Model;

import jakarta.persistence.Entity;
import Model.Atendente;

@Entity
public class Administrador extends Atendente{
    
    public Administrador(String CPF, String nome) {
        super(CPF, nome);
    }
    
    
}
