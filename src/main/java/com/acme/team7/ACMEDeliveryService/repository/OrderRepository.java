package com.acme.team7.ACMEDeliveryService.repository;

import com.acme.team7.ACMEDeliveryService.domain.Order;
import com.acme.team7.ACMEDeliveryService.transfer.KeySixValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(name = "Order.GetAccountOrders",nativeQuery = true)
    List<KeySixValues<String, Date,String, BigDecimal,Integer,BigDecimal,String>> getAccountOrders(Long id);
}
