/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Joke;
import dto.JokeDTO;
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
 * @author Marks
 */
public class JokeFacade {
     private static JokeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private JokeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    
    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> query =  em.createQuery("SELECT m FROM Members m",Joke.class);
        List<Joke> jokes = query.getResultList();
        List<JokeDTO> jokeDTOs = new ArrayList();
        jokes.forEach((Joke joke) -> {
            jokeDTOs.add(new JokeDTO(joke));
        });
        return jokeDTOs;     
    }
    
    public MembersDTO getMemberById(int id) {
        EntityManager em = emf.createEntityManager();
        Members m = em.find(Members.class, id);
        return new MembersDTO(m);
    }
    
    
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getJokeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long getMembersCount = (long)em.createQuery("SELECT COUNT(m) FROM Joke m").getSingleResult();
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
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(new Joke("some joke","Joke.com"));
            em.persist(new Joke("??","idk"));
            em.persist(new Joke("joker","yes"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
