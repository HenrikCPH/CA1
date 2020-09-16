package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import facades.CarFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Yones
 */
@Path("Car")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final CarFacade FACADE =  CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    /**
     * Creates a new instance of JokeResource
     */
    public CarResource() {
    }

//@Path("/{id}")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public CarDTO getById(@PathParam("id") int id) {
//        return FACADE.getCarById(id);
//        //throw new UnsupportedOperationException();
//    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        //throw new UnsupportedOperationException();
        List<CarDTO> cars = FACADE.getAllCars();
      return new Gson().toJson(cars);
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
