package org.kata.axxes.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.kata.axxes.api.requests.OrderRequest;
import org.kata.axxes.domain.Person;
import org.kata.axxes.domain.ProductOrder;
import org.kata.axxes.exceptions.UnknownUserException;

@ApplicationScoped
public class OrderService {

    @Inject
    private AuthenticationService authenticationService;

    public void placeOrder(OrderRequest orderRequest) throws UnknownUserException {
        Person person = authenticationService.findPersonById(orderRequest.getPersonId());
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductName(orderRequest.getProduct());
        productOrder.setQuantity(orderRequest.getQuantity());
        productOrder.setPrice(orderRequest.getPrice());
        productOrder.setShippingAddress(orderRequest.getShippingAddress());
        productOrder.setBillingAddress(orderRequest.getBillingAddress());
        productOrder.setPerson(person);
        productOrder.persist();
    }
}
