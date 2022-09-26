package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.repository.OrderRepository;
import com.acme.team7.ACMEDeliveryService.transfer.KeySixValues;
import com.acme.team7.ACMEDeliveryService.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    public Order initiateOrder(Account account) {
        return Order.builder().account(account).orderItems(new HashSet<>()).build();
    }
    @Override
    public void addItem(Order order, StoreProduct storeProduct, int quantity) {
        if (isNullOrderStoreProduct(order,storeProduct)) {
            return;
        }
        if (order.getOrderItems().isEmpty()) {
            logger.info("Adding first storeProduct to order.");
            order.getOrderItems().add(orderItemCreation(order,storeProduct,quantity));
            return;
        }
        if (isSameStore(order,storeProduct)) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (orderItem.getStoreProduct().getId().equals(storeProduct.getId())) {
                    orderItem.setQuantity(orderItem.getQuantity() + quantity);
                    logger.debug("Quantity of the storeProduct {}, changed.",storeProduct);
                    return;
                }
            }
            order.getOrderItems().add(orderItemCreation(order, storeProduct, quantity));
            logger.debug("StoreProduct {}, added to basket.",storeProduct);
        }
        if (!isSameStore(order,storeProduct)) {
            logger.warn("New store selection.");
            order.getOrderItems().removeAll(order.getOrderItems());
            logger.debug("StoreProducts removed from order {}.",order);

            logger.debug("Adding new item on the order."); //AOP!!!!!!
            order.getOrderItems().add(orderItemCreation(order,storeProduct,quantity));
        }
    }

    @Override
    public void updateItem(Order order, StoreProduct storeProduct, int quantity) {
        if (isNullOrderStoreProduct(order,storeProduct)) {
            return;
        }
        if (orderItemExistence(order, storeProduct) == null) {
            logger.debug("Unable to update storeProduct not existing in the order.");
            return;
        }
        if (orderItemExistence(order, storeProduct) != null) {
            OrderItem orderItem = orderItemExistence(order,storeProduct);
            order.getOrderItems().remove(orderItem);
            order.getOrderItems().add(orderItemCreation(order, storeProduct, quantity));
            logger.info("StoreProduct [{}] updated in order [{}]",storeProduct,order);
        }
    }

    @Override
    public void removeItem(Order order, StoreProduct storeProduct) {
        if (isNullOrderStoreProduct(order,storeProduct)) {
            return;
        }
        if (orderItemExistence(order, storeProduct) == null) {
            logger.debug("Unable to remove storeProduct not existing in the order.");
            return;
        }
        if (orderItemExistence(order, storeProduct) != null) {
            order.getOrderItems().remove(orderItemExistence(order, storeProduct));
            logger.warn("StoreProduct {} removed from the order {}.",storeProduct,order);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Order checkout(Order order) {
        if (isNullOrderPayment(order,order.getPaymentMethod())) {
            return null;
        }
        order.setTotalCost(computeTotalCost(order));
        order.setSubmissionDate(new Date());
        return create(order);
    }

    @Override
    public Order proceedToCheckout(Order order, PaymentMethod paymentMethod) {
        if (isNullOrderPayment(order,paymentMethod)) {
            return null;
        }
        order.setPaymentMethod(paymentMethod);
        return order;
    }

    @Override
    public List<KeySixValues<String,Date,String,BigDecimal,Integer,BigDecimal,String>> getAccountOrders(Long id) {
        List<KeySixValues<String,Date,String,BigDecimal,Integer,BigDecimal,String>> orders = orderRepository.getAccountOrders(id);
        if (!orders.isEmpty()) {
            return orders;
        }
        throw new NoSuchElementException(String.format("No orders found."));
    }

    private BigDecimal computeTotalCost(Order order) {
        BigDecimal cost = new BigDecimal(0);
        for (OrderItem orderItem : order.getOrderItems()) {
            cost = cost.add(orderItem.getOrderPrice());
        }
        return cost;
    }

    private boolean isSameStore(Order order, StoreProduct storeProduct) {
        if (order.getOrderItems().iterator().next().getStoreProduct().getStore().getId().equals(storeProduct.getStore().getId())) {
            return true;
        }
        logger.error("Unable to add item from a different store.");
        return false;
    }

    private boolean isNullOrderPayment(Order order, PaymentMethod paymentMethod) {
        if (order == null) {
            logger.error("Invalid null order.");
            return true;
        }
        if (order.getOrderItems().isEmpty()){
            logger.error("Basket is empty.");
            return true;
        }
        if (paymentMethod == null) {
            logger.error("Invalid null payment method.");
            return true;
        }
        return false;
    }

    private boolean isNullOrderStoreProduct(Order order, StoreProduct storeProduct) {
        if (order == null) {
            logger.warn("Invalid null order.");
            return true;
        }
        if (storeProduct == null) {
            logger.warn("Invalid null storeProduct.");
            return true;
        }
        return false;
    }

    private OrderItem orderItemExistence(Order order,StoreProduct storeProduct){
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getStoreProduct().getId().equals(storeProduct.getId())) {
                return orderItem;
            }
        }
        return null;
    }

    private OrderItem orderItemCreation(Order order, StoreProduct storeProduct, int quantity) {
        return OrderItem.builder().storeProduct(storeProduct).order(order).quantity(quantity).orderPrice(storeProduct.getPrice()).build();
    }
}
