package com.acme.team7.ACMEDeliveryService.repository;
import com.acme.team7.ACMEDeliveryService.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}

