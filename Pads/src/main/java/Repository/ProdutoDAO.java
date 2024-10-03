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

    
    public Produto readByNome(String nome) {
        EntityManager em = emf.createEntityManager();
        Produto produto = null;

        try {
            produto = em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class)
                        .setParameter("nome", nome)
                        .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Produto não encontrado com nome: " + nome);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return produto;
}

public Produto readById(long id) {
    EntityManager em = emf.createEntityManager();
    Produto produto = null;

    try {
        produto = em.find(Produto.class, id);
    } catch (NoResultException e) {
        System.out.println("Produto não encontrado com id: " + id);
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

    
    public void removeByNome(String nome) {
    EntityManager em = emf.createEntityManager();

    try {
        em.getTransaction().begin();
        Produto produto = em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class)
                            .setParameter("nome", nome)
                            .getSingleResult();

        if (produto != null) {
            em.remove(produto);
            em.getTransaction().commit();
        }
    } catch (NoResultException e) {
        System.out.println("Produto não encontrado com nome: " + nome);
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
