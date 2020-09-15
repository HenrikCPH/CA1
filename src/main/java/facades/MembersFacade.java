package facades;

import entities.RenameMe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MembersFacade {

    private static MembersFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MembersFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MembersFacade getMembersFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MembersFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getMembersCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long getMembersCount = (long)em.createQuery("SELECT COUNT(m) FROM Member m").getSingleResult();
            return getMembersCount;
        }finally{  
            em.close();
        }
        
    }
public static void main(String[] args) {
        //Create emf pointing to the dev-database
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Members").executeUpdate();
            em.persist(new Member("","",""));
            em.persist(new Member("","",""));
            em.persist(new Member("","",""));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
    
 
   
