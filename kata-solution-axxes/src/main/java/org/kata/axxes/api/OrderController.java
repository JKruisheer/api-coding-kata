package org.kata.axxes.api;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kata.axxes.api.requests.OrderRequest;
import org.kata.axxes.exceptions.UnknownUserException;
import org.kata.axxes.service.OrderService;

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
}
