package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Order;
import com.acme.team7.ACMEDeliveryService.domain.Product;
import com.acme.team7.ACMEDeliveryService.repository.OrderRepository;
import com.acme.team7.ACMEDeliveryService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public JpaRepository<Product, Long> getRepository() {

        return productRepository;
    }
    @Override
   public List<Product>reportTopProducts() {
        return productRepository.reportTopProducts();
    }


}
