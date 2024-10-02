package Repository;

import Model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;


public class ClienteDAO {

        private final EntityManagerFactory emf;
        private final EntityManager em;
        
public ClienteDAO() {
        emf = Persistence.createEntityManagerFactory("Pads");
        em = emf.createEntityManager();
    }
     
public void create (Cliente cliente){
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

public Cliente remove(String CPF){
        Cliente cliente = null;
        
        try {
            em.getTransaction().begin();
            cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.CPF = :CPF", Cliente.class)
                    .setParameter("CPF", CPF)
                    .getSingleResult();
            
            if(cliente != null) {
                em.remove(cliente);
                em.getTransaction().commit();
            }   
            
        } catch (NoResultException e) {
        System.out.println("Cliente não encontrado com CPF: " + CPF);
        em.getTransaction().rollback();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return cliente;
    }

public void update(Cliente c) {
       
        
        try{
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally{
            closeConnection();
        }
    }

 public Cliente read (String CPF){
        Cliente cliente = null;
        try {
            em.getTransaction().begin();
            cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.CPF = :CPF", Cliente.class)
                    .setParameter("CPF", CPF)
                    .getSingleResult();
            em.getTransaction().commit();
            
        } catch (NoResultException e) {
        System.out.println("Cliente não encontrado com CPF: " + CPF);
        em.getTransaction().rollback();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            
        }
        
        return cliente;
    }    
    public void closeConnection(){ //método para fechar os EM e EMF
        em.close();
        emf.close();
    }

}
    
