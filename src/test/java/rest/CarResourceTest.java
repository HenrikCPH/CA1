/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Car;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Marks
 */
public class CarResourceTest {
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private Car c1;
    private Car c2;
    private Car c3;
    private Car c4;
    private Car c5;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
       // First Drop and Rebuild the test database 
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        //Set System property so the project executed by the Grizly-server wil use this same database
        EMF_Creator.startREST_TestWithDB();

        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {

        // System.in.read();
        httpServer.shutdownNow();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
    }

     //Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
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
            em.createQuery("DELETE from Members").executeUpdate();
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

    @Test
    public void serverIsRunning() {
        System.out.println("Testing is server UP");
       // Gherkin Syntax
        given().when().get("/members").then().statusCode(200);
       // Hamcrest matcher
        given().when().get("/members").then().assertThat().statusCode(200);
    }

    @Test
    public void contentType() {
        //Gherkin Syntax
        given().when().get("/members").then().contentType(ContentType.JSON);
       // Hamcrest matcher
        given().when().get("/members").then().assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void demonStrateLogging() {

        given().log().all().when().get("/Car").then().log().body();

    }


<<<<<<< HEAD
    @Test
    public void testGetAll() {
        given()
                .get("/Car/all")
                .then()
                .assertThat()
                .body("size()", equalTo(5))
                .body("make", hasItems("Ford","Jeep","Volvo","Chevy","Chevy"));
    }
=======
>>>>>>> 851ee95dbe907d75039f52c618fa9dc9b7d7018e

//    @Test
//    public void testGetAll() {
//        given()
//                .get("/Car/all")
//                .then()
//                .assertThat()
//                .body("size()", equalTo(20))
//                .body("make", hasItems("Ford","Jeep","Volvo","Chevy","Chevy"));
//    }

   

//    @Test
//    public void testFindById() {
//        given()
//                .contentType("application/json")
//                .get("/Car/make/Ford").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("[0].id", equalTo(c1.getId()));
//    }
}
