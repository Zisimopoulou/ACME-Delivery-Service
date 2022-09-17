package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.ProductCategory;
import com.acme.team7.ACMEDeliveryService.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory> implements ProductCategoryService {
    private final ProductCategoryRepository productRepository;

    @Override
    public JpaRepository<ProductCategory, Long> getRepository() {
        return productRepository;
    }

}
