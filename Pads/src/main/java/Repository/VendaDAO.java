package Repository;

import Model.Cliente;
import Model.Produto;
import Model.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class VendaDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pads");

    
    public void create(List<Produto> produtos, Cliente cliente) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            
            Venda venda = new Venda(cliente);

           
            for (Produto p : produtos) {
                venda.addProduto(p);
            }

            
            em.persist(venda);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

   
    public Venda buscarPorId(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    
    public List<Venda> listarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM Venda v", Venda.class).getResultList();
        } finally {
            em.close();
        }
    }

    
    public void update(Venda venda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(venda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

   
    public void remove(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Venda venda = em.find(Venda.class, id);
            if (venda != null) {
                em.remove(venda);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
