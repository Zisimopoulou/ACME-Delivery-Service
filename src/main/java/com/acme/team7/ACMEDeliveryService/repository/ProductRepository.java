package com.acme.team7.ACMEDeliveryService.repository;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> reportTopProducts();
}

