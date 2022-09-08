package com.acme.team7.ACMEDeliveryService.service;
import com.acme.team7.ACMEDeliveryService.domain.Product;
import java.util.List;

public interface ProductService extends BaseService<Product> {
    List<Product> reportTopProducts();
    Product findByName(String name);
}

