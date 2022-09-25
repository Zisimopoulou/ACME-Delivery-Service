package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.domain.Order;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.OrderService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import com.acme.team7.ACMEDeliveryService.transfer.KeySixValues;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderControllerImpl extends BaseControllerImpl<Order> {

    private final OrderService orderService;

    @Override
    public BaseService<Order> getBaseService() {
        return orderService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Order>> create(@Valid @RequestBody final Order entity) {
        return new ResponseEntity<>(ApiResponse.<Order>builder().data(orderService.checkout(entity)).build(),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "accountOrders/{id}")
    public ResponseEntity<ApiResponse<List<KeySixValues<String, Date,String, BigDecimal,Integer,BigDecimal,String>>>> findOrdersByAccount(@PathVariable("id") final Long id) {
        final List<KeySixValues<String, Date,String, BigDecimal,Integer,BigDecimal,String>> orders = orderService.getAccountOrders(id);
        if (orders == null) {
            throw new NoSuchElementException("No orders found");
        }
        return ResponseEntity.ok(ApiResponse.<List<KeySixValues<String, Date,String, BigDecimal,Integer,BigDecimal,String>>>builder().data(orders).build());
    }
}

