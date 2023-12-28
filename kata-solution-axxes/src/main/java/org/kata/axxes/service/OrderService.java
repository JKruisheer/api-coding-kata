package org.kata.axxes.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.kata.axxes.api.requests.OrderRequest;
import org.kata.axxes.domain.person.Person;
import org.kata.axxes.domain.productorder.ProductOrder;
import org.kata.axxes.domain.productorder.ProductOrderRepository;
import org.kata.axxes.domain.productorder.ProductOrderSum;
import org.kata.axxes.exceptions.UnknownUserException;

import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private ProductOrderRepository productOrderRepository;

    public void placeOrder(OrderRequest orderRequest) throws UnknownUserException {
        Person person = authenticationService.findPersonById(orderRequest.getPersonId());
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductName(orderRequest.getProduct());
        productOrder.setQuantity(orderRequest.getQuantity());
        productOrder.setPrice(orderRequest.getPrice());
        productOrder.setShippingAddress(orderRequest.getShippingAddress());
        productOrder.setBillingAddress(orderRequest.getBillingAddress());
        productOrder.setPerson(person);
        productOrderRepository.persist(productOrder);
    }

    public List<ProductOrderSum> fetchReport() {
        return productOrderRepository.calculateTotalSumOfProducts();
    }
}
