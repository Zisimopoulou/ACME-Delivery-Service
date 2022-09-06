package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface OrderController extends BaseController<Order> {
    ResponseEntity<ApiResponse<Order>> initiateOrder(Account account);

    void addItem(Order order, StoreProduct storeProduct, int quantity);

    void updateItem(Order order, StoreProduct storeProduct, int quantity);

    void removeItem(Order order, StoreProduct storeProduct);

    ResponseEntity<ApiResponse<Order>> proceedToCheckout(Order order, PaymentMethod paymentMethod);

//    ResponseEntity<ApiResponse<Order>> getLazyOrders(Long id);
}
