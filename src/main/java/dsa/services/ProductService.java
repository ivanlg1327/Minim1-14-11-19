package dsa.services;

import dsa.models.Product;
import dsa.GameManager;
import dsa.GameManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//import dsa.utils.ProductManager;


@Api(value = "/products", description = "Endpoint to Product Service")
@Path("/products")
public class ProductService {


    private GameManager tm;

    public ProductService() {
        this.tm = GameManagerImp.getInstance();
        if (tm.size()==0) {
            this.tm.addProduct("La Barbacoa", "Georgie Dann", 3);
            this.tm.addProduct("Despacito", "Basura", 2);
            this.tm.addProduct("Enter Sandman", "Metallica", 5);
        }
    }

    @GET
    @ApiOperation(value = "get all products", notes = "Here we see all the products that we have available")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {

        List<Product> products = this.tm.listPrices();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build() ;

    }

    @GET
    @ApiOperation(value = "get a Product", notes = "We look for an specific product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Product t = this.tm.getProduct(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
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
    }

    @PUT
    @ApiOperation(value = "update a Product", notes = "We have a new offer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/")
    public Response updateProduct(Product product) {

        Product t = this.tm.updateProduct(product);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Product", notes = "We have a brand new product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Product.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct(Product product) {

        if (product.getName()==null || product.getPrice()==0)  return Response.status(500).entity(product).build();
        this.tm.addProduct(product.name, product.description, product.price);
        return Response.status(201).entity(product).build();
    }

}
