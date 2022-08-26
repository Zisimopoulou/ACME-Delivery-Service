package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory>implements ProductCategoryService{
    @Override
    public JpaRepository<ProductCategory, Long> getRepository() {
        return null;
    }
}