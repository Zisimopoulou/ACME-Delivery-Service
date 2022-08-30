package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.repository.OrderRepository;
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
@Slf4j
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
    public void addItem(Order order, Product product, int quantity) {
        if (isNullOrderProduct(order,product)) {
            return;
        }
        if (order.getOrderItems().isEmpty()) {
            log.info("Adding first product to order.");
            order.getOrderItems().add(orderItemCreation(order,product,quantity));
            return;
        }
        if (isSameStore(order,product)) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (orderItem.getProduct().getSerial().equals(product.getSerial())) {
                    orderItem.setQuantity(orderItem.getQuantity() + quantity);
                    log.debug("Quantity of the product {}, changed.",product);
                    return;
                }
            }
            order.getOrderItems().add(orderItemCreation(order, product, quantity));
            log.debug("Product {}, added to basket.",product);
        }
        if (!isSameStore(order,product)) {
            log.warn("New store selection.");
            order.getOrderItems().removeAll(order.getOrderItems());
            log.debug("Products removed from order {}.",order);

            log.debug("Adding new item on the order."); //AOP!!!!!!
            order.getOrderItems().add(orderItemCreation(order,product,quantity));
        }
    }

    @Override
    public void updateItem(Order order, Product product, int quantity) {
        if (isNullOrderProduct(order,product)) {
            return;
        }
        if (orderItemExistence(order, product) == null) {
            log.debug("Unable to update product not existing in the order.");
            return;
        }
        if (orderItemExistence(order, product) != null) {
            OrderItem orderItem = orderItemExistence(order,product);
            order.getOrderItems().remove(orderItem);
            order.getOrderItems().add(orderItemCreation(order, product, quantity));
            log.info("Product [{}] updated in order [{}]",product,order);
        }
    }

    @Override
    public void removeItem(Order order, Product product) {
        if (isNullOrderProduct(order,product)) {
            return;
        }
        if (orderItemExistence(order, product) == null) {
            log.debug("Unable to remove product not existing in the order.");
            return;
        }
        if (orderItemExistence(order, product) != null) {
            order.getOrderItems().remove(orderItemExistence(order, product));
            log.warn("Product {} removed from the order {}.",product,order);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Order checkout(Order order, PaymentMethod paymentMethod) {
        if (isNullOrderPayment(order,paymentMethod)) {
            return null;
        }
        order.setTotalCost(computeTotalCost(order));
        order.setSubmissionDate(new Date());
        order.setPaymentMethod(paymentMethod);
        return create(order);
    }

    @Override
    public Order getLazyOrders(Long id) {
        Optional<Order> order = orderRepository.getLazyOrders(id);
        if (order.isPresent()) {
            return order.get();
        }
        throw new NoSuchElementException(String.format("There was no order found matching id %d.", id));
    }

    private BigDecimal computeTotalCost(Order order) {
        BigDecimal cost = new BigDecimal(0);
        for (OrderItem orderItem : order.getOrderItems()) {
            cost = cost.add(orderItem.getPrice());
        }
        return cost;
    }

    private boolean isSameStore(Order order, Product product) {
        if (order.getOrderItems().iterator().next().getProduct().getStore().getId().equals(product.getStore().getId())) {
            return true;
        }
        log.error("Unable to add item from a different store.");
        return false;
    }

    private boolean isNullOrderPayment(Order order, PaymentMethod paymentMethod) {
        if (order == null) {
            log.warn("Invalid null order.");
            return true;
        }
        if (order.getOrderItems().isEmpty()){
            log.warn("Basket is empty.");
            return true;
        }
        if (paymentMethod == null) {
            log.warn("Invalid null payment method.");
            return true;
        }
        return false;
    }

    private boolean isNullOrderProduct(Order order, Product product) {
        if (order == null) {
            log.warn("Invalid null order.");
            return true;
        }
        if (product == null) {
            log.warn("Invalid null product.");
            return true;
        }
        return false;
    }

    private OrderItem orderItemExistence(Order order,Product product){
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getProduct().getId().equals(product.getId())) {
                return orderItem;
            }
        }
        return null;
    }

    private OrderItem orderItemCreation(Order order, Product product, int quantity) {
        return OrderItem.builder().product(product).order(order).quantity(quantity).price(product.getPrice()).build();
    }
}
