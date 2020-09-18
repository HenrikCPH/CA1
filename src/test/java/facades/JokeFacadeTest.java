/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Joke;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

public class JokeFacadeTest {
    
    public JokeFacadeTest() {
    }
    
     private static  EntityManagerFactory emf;
    private static  JokeFacade facade;
    
    private Joke j1;
    private Joke j2;
    private Joke j3;
    
    
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getJokeFacade(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
          j1 = new Joke("some joke","Joke.com");
          j2 = new Joke("??","idk");
          j3 = new Joke("joker","yes");

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testgetMembersCount() {
        assertEquals(3,facade.getJokeCount(),"Expects three movies in the database");
    
    }
    
    @Test
    public void testGetAllMembers(){
        //Tode
    }

    @Test
    public void testGetMemberById(){
        //Todo
    }
    
    @Test
    public void testMembersHasmovies(){
        //You could use the method: arrayContaining(....
    }
    
    @Test
    public void getMembersByname(){
        //Todo
    }
}
