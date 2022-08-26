package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.domain.Order;
import com.acme.team7.ACMEDeliveryService.domain.Product;
import com.acme.team7.ACMEDeliveryService.domain.Store;

import java.util.List;

public interface OrderService extends BaseService<Order> {

    Order initiateOrder(Account account);
    void addItem(Order order, Product product, int quantity);

    void updateItem(Order order, Product product, int quantity);

    void removeItem(Order order, Product product);

    Order getLazyOrders(Long id);
}
