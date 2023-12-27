package org.kata.axxes.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.kata.axxes.api.requests.OrderRequest;
import org.kata.axxes.domain.Person;
import org.kata.axxes.domain.ProductOrder;
import org.kata.axxes.domain.ProductOrderRepository;
import org.kata.axxes.exceptions.UnknownUserException;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@QuarkusTest
class OrderServiceTest {

    private static final String ORDER_NAME = "ORDER_NAME";

    @InjectMock
    private ProductOrderRepository productOrderRepository;

    @InjectMock
    private AuthenticationService authenticationService;

    @Inject
    private OrderService orderService;

    @Test
    void placeOrderShouldThrowIfUnknownUser() throws UnknownUserException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setPersonId(99999L);

        when(authenticationService.findPersonById(orderRequest.getPersonId())).thenThrow(UnknownUserException.class);

        assertThrows(UnknownUserException.class, () -> orderService.placeOrder(orderRequest));
    }

    @Test
    void placeOrderShouldBeCalledWithCorrectValues() throws UnknownUserException {
        OrderRequest orderRequest = createOrderRequest();

        Person personMock = mock(Person.class);
        when(personMock.getPersonId()).thenReturn(1L);

        when(authenticationService.findPersonById(1L)).thenReturn(personMock);

        orderService.placeOrder(orderRequest);

        ArgumentCaptor<ProductOrder> argumentCaptor = ArgumentCaptor.forClass(ProductOrder.class);
        verify(productOrderRepository, times(1)).persist(argumentCaptor.capture());

        ProductOrder capturedProductOrder = argumentCaptor.getValue();
        assertEquals(capturedProductOrder.getProductName(), orderRequest.getProduct());
        assertEquals(capturedProductOrder.getPrice(), orderRequest.getPrice());
        assertEquals(capturedProductOrder.getQuantity(), orderRequest.getQuantity());
        assertEquals(capturedProductOrder.getShippingAddress(), orderRequest.getShippingAddress());
        assertEquals(capturedProductOrder.getBillingAddress(), orderRequest.getBillingAddress());
    }

    public OrderRequest createOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProduct(ORDER_NAME);
        orderRequest.setPrice(BigDecimal.TEN);
        orderRequest.setQuantity(5);
        orderRequest.setShippingAddress("123");
        orderRequest.setBillingAddress("123");
        return orderRequest;
    }

}