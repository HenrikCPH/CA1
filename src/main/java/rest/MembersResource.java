package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MembersDTO;
import utils.EMF_Creator;
import facades.MembersFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("members")
public class MembersResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MembersFacade FACADE =  MembersFacade.getMembersFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getMembersCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public MembersDTO getById(@PathParam("id") int id) {
        return FACADE.getMemberById(id);
        //throw new UnsupportedOperationException();
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        //throw new UnsupportedOperationException();
        List<MembersDTO> Members = FACADE.getAllMembers();
        return new Gson().toJson(Members);
    }
  
}
