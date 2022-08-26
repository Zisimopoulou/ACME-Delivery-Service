package com.acme.team7.ACMEDeliveryService.repository;
import com.acme.team7.ACMEDeliveryService.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true)

    List<Product> reportTopProducts();
    Product findProductByName(String name);
}

