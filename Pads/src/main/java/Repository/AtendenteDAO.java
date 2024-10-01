package Repository;

import Model.Atendente;
import Model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class AtendenteDAO {
        private final EntityManagerFactory emf;
        private final EntityManager em;
        
        AtendenteDAO(){
            emf = Persistence.createEntityManagerFactory("Pads");
            em = emf.createEntityManager();
        }
        
    public void gravar(Atendente atendente){
            try {
            em.getTransaction().begin();
            em.persist(atendente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    public Atendente remover(String CPF){
        Atendente at = null;
        
        try {
            em.getTransaction().begin();
            at = (Atendente) em.createQuery("SELECT c FROM Atendente c WHERE c.CPF = :CPF", Atendente.class)
                    .setParameter("CPF", CPF)
                    .getSingleResult();
            
            if(at != null) {
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
            closeConnection();
        }
        
        return at;
    }

public void atualizar(Atendente a) {
        try{
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally{
            closeConnection();
        }
    }
        
        
    public void closeConnection(){ //método para fechar os EM e EMF
        em.close();
        emf.close();
    }
        
}

 
    

