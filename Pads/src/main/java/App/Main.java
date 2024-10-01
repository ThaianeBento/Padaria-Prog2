package org.example.App;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Model.Pessoa;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pads");
        final EntityManager em = emf.createEntityManager();

        Pessoa p = new Pessoa();
        p.nome = "Lucas";
        p.sobrenome = "Castro";
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
