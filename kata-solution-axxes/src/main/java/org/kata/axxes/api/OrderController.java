package org.kata.axxes.api;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kata.axxes.api.requests.OrderRequest;
import org.kata.axxes.domain.productorder.ProductOrderSum;
import org.kata.axxes.exceptions.UnknownUserException;
import org.kata.axxes.service.OrderService;

import java.util.List;

@Path("/orders")
public class OrderController {

    @Inject
    private OrderService orderService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/create")
    public Response storeOrder(OrderRequest orderRequest) throws UnknownUserException {
        orderService.placeOrder(orderRequest);
        return Response.ok("Order " + orderRequest.getProduct() + " has been created").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/report")
    public List<ProductOrderSum> fetchReport() {
        return orderService.fetchReport();
    }
}
