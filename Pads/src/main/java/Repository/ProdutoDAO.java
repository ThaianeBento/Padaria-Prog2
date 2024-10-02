package Repository;

import Model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProdutoDAO {
    private final EntityManagerFactory emf;

    public ProdutoDAO() {
        emf = Persistence.createEntityManagerFactory("Pads");
    }
    
    public void create(Produto produto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produto);  
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();  
        }
    }

    
    public Produto read(Long id) {
        EntityManager em = emf.createEntityManager();
        Produto produto = null;

        try {
            produto = em.find(Produto.class, id);  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();  
        }

        return produto;
    }

    
    public List<Produto> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Produto> produtos = null;

        try {
            produtos = em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();  
        }

        return produtos;
    }

    
    public void update(Produto produto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(produto);  
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();  
        }
    }

    
    public void remove(Long id) {
        EntityManager em = emf.createEntityManager();
        Produto produto = null;

        try {
            em.getTransaction().begin();
            produto = em.find(Produto.class, id); 
            if (produto != null) {
                em.remove(produto);  
                em.getTransaction().commit();
            }
        } catch (NoResultException e) {
            System.out.println("Produto não encontrado com ID: " + id);
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
