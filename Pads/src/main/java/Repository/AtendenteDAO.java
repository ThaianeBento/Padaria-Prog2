package Repository;

import Model.Atendente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class AtendenteDAO {
    private final EntityManagerFactory emf;

    
    public AtendenteDAO() {
        emf = Persistence.createEntityManagerFactory("Pads");
    }

    
    public void create(Atendente atendente) {
        EntityManager em = emf.createEntityManager();  // Criar EntityManager para cada operação
        try {
            em.getTransaction().begin();
            em.persist(atendente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Esse cpf já foi usado.");
        } finally {
            em.close();  // Fecha o EntityManager após cada operação
        }
    }

 
    public Atendente remove(String CPF) {
        EntityManager em = emf.createEntityManager();
        Atendente at = null;

        try {
            em.getTransaction().begin();
            at = em.createQuery("SELECT c FROM Atendente c WHERE c.CPF = :CPF", Atendente.class)
                    .setParameter("CPF", CPF)
                    .getSingleResult();

            if (at != null) {
                em.remove(at);
                em.getTransaction().commit();
            }
        } catch (NoResultException e) {
            System.out.println("Atendente não encontrado com CPF: " + CPF);
            em.getTransaction().rollback();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return at;
    }

    
    public void update(Atendente a) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Atendente readByCPF(String cpf) {
        EntityManager em = emf.createEntityManager();
        Atendente at = null;

        try {
            at = em.createQuery("SELECT a FROM Atendente a WHERE a.cpf = :cpf", Atendente.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();  
        } catch (NoResultException e) {
            System.out.println("Atendente não encontrado com CPF: " + cpf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();  
        }

        return at;
    }
    
    public void closeFactory() {
        emf.close();
    }
}

 
    

