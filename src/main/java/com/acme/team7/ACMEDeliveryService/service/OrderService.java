package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.transfer.KeySixValues;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService extends BaseService<Order> {

    Order initiateOrder(Account account);

    void addItem(Order order, StoreProduct storeProduct, int quantity);

    void updateItem(Order order, StoreProduct storeProduct, int quantity);

    void removeItem(Order order, StoreProduct storeProduct);

    Order checkout(Order order);

    Order proceedToCheckout(Order order, PaymentMethod paymentMethod);

    List<KeySixValues<String,Date,String,BigDecimal,Integer,BigDecimal,String>> getAccountOrders(Long id);

}
