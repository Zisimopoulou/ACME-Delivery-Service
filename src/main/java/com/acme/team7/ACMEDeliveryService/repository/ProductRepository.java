package com.acme.team7.ACMEDeliveryService.repository;
import com.acme.team7.ACMEDeliveryService.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//  @Query(value= "SELECT
//  STOREPRODUCT_ID,COUNT(STOREPRODUCT_ID) Frequency"+"FROM ORDER_ITEMS"+"GROUP BY STOREPRODUCT_ID"+"ORDER BY COUNT(STOREPRODUCT_ID) DESC"+"FETCH NEXT 1 ROWS ONLY", nativeQuery = true)
    @Query(nativeQuery = false)
    List<Product> reportTopProducts();
    Product findProductByName(String name);
}

