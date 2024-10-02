package Repository;

import Model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class ClienteDAO {

    private final EntityManagerFactory emf;

    public ClienteDAO() {
        emf = Persistence.createEntityManagerFactory("Pads");
    }

    public void create(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();  
        }
    }

    
    public Cliente remove(String cpf) {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = null;

        try {
            em.getTransaction().begin();
            cliente = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class)
                    .setParameter("CPF", cpf)
                    .getSingleResult();

            if (cliente != null) {
                em.remove(cliente);
                em.getTransaction().commit();
            }
        } catch (NoResultException e) {
            System.out.println("Cliente não encontrado com CPF: " + cpf);
            em.getTransaction().rollback();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();  
        }

        return cliente;
    }

    
    public void update(Cliente c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);  
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();  
        }
    }

    
    public Cliente read(String cpf) {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = null;

        try {
            cliente = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class)
                    .setParameter("CPF", cpf)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Cliente não encontrado com CPF: " + cpf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); 
        }

        return cliente;
    }

    
    public void closeFactory() {
        emf.close();
    }
}

    
