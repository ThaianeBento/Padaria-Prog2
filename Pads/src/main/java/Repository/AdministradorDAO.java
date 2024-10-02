package Repository;

import Model.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.List;

public class AdministradorDAO {
    private final EntityManagerFactory emf;

    public AdministradorDAO() {
        emf = Persistence.createEntityManagerFactory("Pads");
    }

    
    public void create(Administrador administrador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(administrador);  
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();  
        }
    }

    
    public Administrador readByCPF(String cpf) {
        EntityManager em = emf.createEntityManager();
        Administrador administrador = null;

        try {
            administrador = em.createQuery("SELECT a FROM Administrador a WHERE a.cpf = :cpf", Administrador.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();  
        } catch (NoResultException e) {
            System.out.println("Administrador não encontrado com CPF: " + cpf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();  
        }

        return administrador;
    }

    
    public List<Administrador> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Administrador> administradores = null;

        try {
            administradores = em.createQuery("SELECT a FROM Administrador a", Administrador.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); 
        }

        return administradores;
    }

    
    public void update(Administrador administrador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(administrador);  
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();  
        }
    }

    
    public void removeByCPF(String cpf) {
        EntityManager em = emf.createEntityManager();
        Administrador administrador = null;

        try {
            em.getTransaction().begin();
            administrador = em.createQuery("SELECT a FROM Administrador a WHERE a.cpf = :cpf", Administrador.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();  
            if (administrador != null) {
                em.remove(administrador);  
                em.getTransaction().commit();
            }
        } catch (NoResultException e) {
            System.out.println("Administrador não encontrado com CPF: " + cpf);
            em.getTransaction().rollback();
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


