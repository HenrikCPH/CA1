package facades;

import dto.MembersDTO;
import entities.Members;
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
    
    public List<MembersDTO> getAllMembers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Members> query =  em.createQuery("SELECT m FROM Members m",Members.class);
        List<Members> members = query.getResultList();
        List<MembersDTO> membersDTOs = new ArrayList();
        members.forEach((Members member) -> {
            membersDTOs.add(new MembersDTO(member));
        });
        return membersDTOs;     
    }
    
    public MembersDTO getMemberById(int id) {
        EntityManager em = emf.createEntityManager();
        Members m = em.find(Members.class, id);
        return new MembersDTO(m);
    }
    
     public List<MembersDTO> getMembersByName(String title) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Members> query = em.createQuery("SELECT m FROM Members m WHERE m.name LIKE :title", Members.class);
        query.setParameter("title", "%"+title+"%");
        List<Members> members = query.getResultList();
        List<MembersDTO> membersDTOs = new ArrayList();
        members.forEach((Members member) -> {
            membersDTOs.add(new MembersDTO(member));
        });
        return membersDTOs;
    }
    
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
            em.persist(new Members("Mark Sørensen","cph-ms845","Tenet"));
            em.persist(new Members("Yones El Bana","cph-ye7","parasite"));
            em.persist(new Members("Henrik Lønquist Thomasen","cph-ht92","1917"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
    
 
   
