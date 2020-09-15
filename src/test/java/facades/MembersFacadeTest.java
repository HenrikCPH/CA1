package facades;

import utils.EMF_Creator;
import entities.Members;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MembersFacadeTest {
    private static  EntityManagerFactory emf;
    private static  MembersFacade facade;
    
    private Members m1;
    private Members m2;
    private Members m3;
    
    public MembersFacadeTest() {
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
<<<<<<< HEAD
          m1 = new Members("Mark Sørensen","cph-ms845","Tenet");
          m2 = new Members("Yones El Bana","cph-ye7","parasite");
          m3 = new Members("Henrik Lønquist Thomasen","cph-ht92","1917");
=======
          m1 = (new Members("Mark Sørensen","cph-ms845","Tenet"));
          m2 = (new Members("Yones El Bana","cph-ye7","parasite"));
          m3 = (new Members("Henrik Lønquist Thomasen","cph-ht92","1917"));
>>>>>>> c5be7e5c96db00b5233bbd0432c5777223f832ed
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Members").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
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
//assertEquals(3,facade.getMembersCount(),"Expects three movies in the database");
    
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