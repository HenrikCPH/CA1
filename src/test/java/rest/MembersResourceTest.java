<<<<<<< HEAD
package rest;

import entities.Members;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@Disabled
public class MembersResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private Members m1;
    private Members m2;
    private Members m3;

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
      m1 = (new Members("Mark Sørensen","cph-ms845","Tenet"));
          m2 = (new Members("Yones El Bana","cph-ye7","parasite"));
          m3 = (new Members("Henrik Lønquist Thomasen","cph-ht92","1917"));
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

        given().log().all().when().get("/members").then().log().body();

    }

    @Test
    public void testgetMembersCount() {
        given().
                get("/members/count")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(3));
    }

    @Test
    public void testGetAll() {
        given()
                .get("/members/all")
                .then()
                .assertThat()
                .body("size()", equalTo(3))
                .body("name", hasItems("Mark Sørensen","Yones El Bana","Henrik Lønquist Thomasen"));
    }

    @Test
    public void testFindByname() {
        given()
                .get("/members/name/Yones El Bana")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    public void testFindByNameNotFound() {
        given()
                .get("/members/name/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }

    @Test
    public void testFindById() {
        given()
                .contentType("application/json")
                .get("/members/name/Mark Sørensen").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("[0].id", equalTo(m1.getId().intValue()));
    }
}
=======
//package rest;
//
//import entities.Members;
//import utils.EMF_Creator;
//import io.restassured.RestAssured;
//import static io.restassured.RestAssured.given;
//import io.restassured.http.ContentType;
//import io.restassured.parsing.Parser;
//import java.io.IOException;
//import java.net.URI;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.ws.rs.core.UriBuilder;
//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.grizzly.http.util.HttpStatus;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.server.ResourceConfig;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.hasItems;
//import static org.hamcrest.Matchers.*;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
////@Disabled
//public class MembersResourceTest {
//
//    private static final int SERVER_PORT = 7777;
//    private static final String SERVER_URL = "http://localhost/api";
//    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
//    private static HttpServer httpServer;
//    private static EntityManagerFactory emf;
//
//    private Members m1;
//    private Members m2;
//    private Members m3;
//
//    static HttpServer startServer() {
//        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
//        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        //First Drop and Rebuild the test database 
//        emf = EMF_Creator.createEntityManagerFactoryForTest();
//
//        //Set System property so the project executed by the Grizly-server wil use this same database
//        EMF_Creator.startREST_TestWithDB();
//
//        httpServer = startServer();
//
//        //Setup RestAssured
//        RestAssured.baseURI = SERVER_URL;
//        RestAssured.port = SERVER_PORT;
//
//        RestAssured.defaultParser = Parser.JSON;
//    }
//
//    @AfterAll
//    public static void closeTestServer() {
//
//        // System.in.read();
//        httpServer.shutdownNow();
//        //Don't forget this, if you called its counterpart in @BeforeAll
//        EMF_Creator.endREST_TestWithDB();
//    }
//
//    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        m1 = (new Members("Mark Sørensen", "cph-ms845", "Tenet"));
//        m2 = (new Members("Yones El Bana", "cph-ye7", "parasite"));
//        m3 = (new Members("Henrik Lønquist Thomasen", "cph-ht92", "1917"));
//        try {
//            em.getTransaction().begin();
//            em.createQuery("DELETE from Members").executeUpdate();
//            em.persist(m1);
//            em.persist(m2);
//            em.persist(m3);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @Test
//    public void serverIsRunning() {
//        System.out.println("Testing is server UP");
//        //Gherkin Syntax
//        given().when().get("/Members").then().statusCode(200);
//        //Hamcrest matcher
//        given().when().get("/Members").then().assertThat().statusCode(200);
//    }
//
//    @Test
//    public void contentType() {
//        //Gherkin Syntax
//        given().when().get("/Members").then().contentType(ContentType.JSON);
//        //Hamcrest matcher
//        given().when().get("/Members").then().assertThat().contentType(ContentType.JSON);
//    }
//
//    @Test
//    public void demonStrateLogging() {
//
//        given().log().all().when().get("/Members").then().log().body();
//
//    }
//
//    @Test
//    public void testgetMembersCount() {
//        given().
//                get("/Members/count")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("count", equalTo(3));
//    }
//
//    @Test
//    public void testGetAll() {
//        given()
//                .get("/Members/all")
//                .then()
//                .assertThat()
//                .body("size()", equalTo(3))
//                .body("title", hasItems("Mark Sørensen", "Yones El Bana", "Henrik Lønquist Thomasen"));
//    }
//
//    @Test
//    public void testFindByname() {
//        given()
//                .get("/Members/name/Yones El Bana")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode());
//    }
//
//    @Test
//    public void testFindByNameNotFound() {
//        given()
//                .get("/Members/name/1")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
//    }
//
//    @Test
//    public void testFindById() {
//        given()
//                .contentType("application/json")
//                .get("/Members/id/1").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("[0].id", equalTo(m1.getId().intValue()));
//    }
//}
>>>>>>> 2bb1f4f58343f1e9a690d73b7cf6b699f4033456
