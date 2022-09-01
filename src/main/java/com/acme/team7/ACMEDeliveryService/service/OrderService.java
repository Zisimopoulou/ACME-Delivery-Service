package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.*;

public interface OrderService extends BaseService<Order> {

    Order initiateOrder(Account account);

    void addItem(Order order, Product product, int quantity);

    void updateItem(Order order, Product product, int quantity);

    void removeItem(Order order, Product product);

    Order checkout(Order order);

    Order proceedToCheckout(Order order, PaymentMethod paymentMethod);

    Order getLazyOrders(Long id);
}
