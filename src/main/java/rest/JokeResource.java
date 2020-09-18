/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.JokeDTO;
import dto.MembersDTO;
import facades.JokeFacade;
import facades.MembersFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final JokeFacade FACADE =  JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    /**
     * Creates a new instance of JokeResource
     */
    public JokeResource() {
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public JokeDTO getById(@PathParam("id") int id) {
        return FACADE.getJokeById(id);
        //throw new UnsupportedOperationException();
    }
    
    @Path("thejoke/{thejoke}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
     public Response getByTheJoke(@PathParam("thejoke") String joken) {
        List<JokeDTO> jokers = FACADE.getJokeByTheJoke(joken);
        if (jokers == null || jokers.isEmpty()) {
            return Response.status(404).entity("{\"code\":404,\"msg\":\"Member not found\"}").build();
        }
        return Response.ok(GSON.toJson(jokers)).build();
    
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        //throw new UnsupportedOperationException();
        List<JokeDTO> Jokers = FACADE.getAllJokes();
        return new Gson().toJson(Jokers);
    } 

    /**
     * PUT method for updating or creating an instance of JokeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
