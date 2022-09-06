package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.domain.Order;
import com.acme.team7.ACMEDeliveryService.domain.PaymentMethod;
import com.acme.team7.ACMEDeliveryService.domain.StoreProduct;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.OrderService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderControllerImpl extends BaseControllerImpl<Order> implements OrderController{

    private final OrderService orderService;

    @Override
    public BaseService<Order> getBaseService() {
        return orderService;
    }

    //Afto linei to provlima??
    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Order>> create(@Valid @RequestBody final Order entity) {
        return new ResponseEntity<>(ApiResponse.<Order>builder().data(orderService.checkout(entity)).build(),
                HttpStatus.CREATED);
    }
}

