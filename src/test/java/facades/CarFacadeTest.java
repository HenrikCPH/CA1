/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Car;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

public class CarFacadeTest {
    
    private static  EntityManagerFactory emf;
    private static  MembersFacade facade;
    
    private Car c1;
    private Car c2;
    private Car c3;
    private Car c4;
    private Car c5;
    
    public CarFacadeTest() {
    }
    
   @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MembersFacade.getMembersFacade(emf);
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
            c1 = (new Car(1, 1997, "Ford", "E350", 3000));
            c2 = (new Car(2, 1999, "Chevy", "Venture", 4900));
            c3 = (new Car(3, 2000, "Chevy", "Venture", 5000));
            c4 = (new Car(4, 1996, "Jeep", "Grand Cherokee", 4799));
            c5 = (new Car(5,  2005, "Volvo", "V70", 44799));

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Car").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.persist(c5);
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
       // assertEquals(5,facade.getMembersCount(),"Expects three movies in the database");
    
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
