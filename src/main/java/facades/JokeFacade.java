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
        TypedQuery<Joke> query =  em.createQuery("SELECT m FROM Joke m",Joke.class);
        List<Joke> jokes = query.getResultList();
        List<JokeDTO> jokeDTOs = new ArrayList();
        jokes.forEach((Joke joke) -> {
            jokeDTOs.add(new JokeDTO(joke));
        });
        return jokeDTOs;     
    }
    
    public JokeDTO getJokeById(int id) {
        EntityManager em = emf.createEntityManager();
        Joke j = em.find(Joke.class, id);
        return new JokeDTO(j);
    }
    
    public List<JokeDTO> getJokeByTheJoke(String joken) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> query = em.createQuery("SELECT m FROM Joke m WHERE m.thejoke LIKE :thejoke", Joke.class);
        query.setParameter("thejoke", "%"+joken+"%");
        List<Joke> jokers = query.getResultList();
        List<JokeDTO> jokeDTOs = new ArrayList();
        jokers.forEach((Joke joke) -> {
            jokeDTOs.add(new JokeDTO(joke));
        });
        return jokeDTOs;
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
            long getJokeCount = (long)em.createQuery("SELECT COUNT(m) FROM Joke m").getSingleResult();
            return getJokeCount;
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
