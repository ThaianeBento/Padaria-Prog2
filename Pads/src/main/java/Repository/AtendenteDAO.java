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

    
    public void gravar(Atendente atendente) {
        EntityManager em = emf.createEntityManager();  // Criar EntityManager para cada operação
        try {
            em.getTransaction().begin();
            em.persist(atendente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();  // Fecha o EntityManager após cada operação
        }
    }

 
    public Atendente remover(String CPF) {
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

    
    public void atualizar(Atendente a) {
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

    
    public void closeFactory() {
        emf.close();
    }
}

 
    

