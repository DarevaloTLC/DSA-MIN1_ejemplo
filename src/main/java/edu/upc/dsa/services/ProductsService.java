package edu.upc.dsa.services;

import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.ProductManager;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/products", description = "Endpoint to Product Service")
@Path("/products")
public class ProductsService {

    private ProductManager pm;

    public ProductsService() {
        this.pm =ProductManagerImpl.getInstance(); //CUANDO USAMOS SINGLETONE NUNCAA!!!! PUEDE SER NULL POR DEFINICION
        if(pm.getProductsSize() == 0){
            pm.addProduct("01", "Apple", 1.00);
            pm.addProduct("02", "Banana", 2.00);
            pm.addProduct("03", "Orange", 3.00);
            pm.addProduct("04", "Watermelon", 4.00);
        }
    }

    @GET
    @ApiOperation(value = "get Products by price", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfull", response = Product.class, responseContainer = "List"),
    })
    @Path("/ByPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByPrice() {
        List<Product> productsList = this.pm.productsByPrice();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productsList) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get Products by sales", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfull", response = Product.class),
    })
    @Path("/BySales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsBySales() {
        List<Product> productsList = this.pm.productsBySales();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productsList) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @POST
    @ApiOperation(value = "add new Order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfull", response = Order.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/NewOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(Order order){
        if(order==null) return Response.status(500).entity(order).build();
        this.pm.addOrder(order);
        return Response.status(201).entity(order).build();
    }

    @PUT
    @ApiOperation(value = "process an Order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfull"),
            @ApiResponse(code = 404, message = "Empty List")
    })
    @Path("/ProcessOrder")
    public Response proccessOrder(){
        Order o = pm.processOrder();
        if(o==null) return Response.status(500).entity(o).build();
        return Response.status(201).entity(o).build();
    }
}
