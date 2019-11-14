package dsa.services;

import dsa.models.Objeto;
import dsa.GameManager;
import dsa.GameManagerImp;
import dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//import dsa.utils.ProductManager;


@Api(value = "/objetos", description = "Endpoint to Product Service")
@Path("/objetos")


public class GameService {


    private GameManager tm;

    public GameService() {
        this.tm = GameManagerImp.getInstance();
        if (tm.size()==0) {
            this.tm.addUser("11111", "Toni", "Oller", "Arcas");
            this.tm.addUser("22222", "Ivan", "Luque", "Garcia");
            Objeto ob1=new Objeto("espasa", 2,"afilada");
            Objeto ob2=new Objeto("escut", 1,"fort");
            this.tm.addUserBag("11111", ob1);
            this.tm.addUserBag("11111", ob2);
        }
    }

    @GET
    @ApiOperation(value = "get all Users", notes = "Here we see all the Users that are online")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        HashMap<String, User> users = this.tm.listUsers();
        List<User> list = new LinkedList<>();
        users.values().forEach(list::add);
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(list) {};
        return Response.status(201).entity(entity).build() ;

    }
    @GET
    @ApiOperation(value = "get all objects from a user", notes = "Here we see all the objects that we have available")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@PathParam("id") String id) {

        List<Objeto> objetos = this.tm.getUserBag(id);

        GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objetos) {};
        return Response.status(201).entity(entity).build() ;

    }

    @GET
    @ApiOperation(value = "get a Product", notes = "We look for an specific product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct( String id) {
        List<Objeto> t = this.tm.getUserBag(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

   /* @DELETE
    @ApiOperation(value = "delete a Product", notes = "We eliminate a product because it is exhausted")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        Product t = this.tm.getProduct(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteProduct(id);
        return Response.status(201).build();
    }*/

    @PUT
    @ApiOperation(value = "update a Product", notes = "We have a new offer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/")
    public Response updateProduct(User user) {

       User t = this.tm.updateUser(user);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Product", notes = "We have a brand new product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct(User user) {

        if (user.getName()==null || user.getId()==null)  return Response.status(500).entity(user).build();
        this.tm.addUser(user.id,user.name, user.surname1, user.surname2);
        return Response.status(201).entity(user).build();
    }

}
