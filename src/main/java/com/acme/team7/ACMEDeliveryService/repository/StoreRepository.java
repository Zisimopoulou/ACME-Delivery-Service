package com.acme.team7.ACMEDeliveryService.repository;

import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.domain.StoreProduct;
import com.acme.team7.ACMEDeliveryService.transfer.TopStoreProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByNameOrStoreCategory_Description(String name, String description);

    Store findStoreById(Long id);

    @Query(
            value = "  SELECT STOREPRODUCT_ID as storeProductId,COUNT(STOREPRODUCT_ID) Frequency \\\n" +
                    "  FROM ORDER_ITEMS \\\n" +
                    "  GROUP BY STOREPRODUCT_ID \\\n" +
                    "  ORDER BY COUNT(STOREPRODUCT_ID) DESC \\\n" +
                    "  FETCH NEXT 10 ROWS ONLY",
            nativeQuery = true)
    List<TopStoreProducts> reportTop10StoreProducts();

//    @Query(nativeQuery = true)
//    List<Store> reportTopStores();
//
//    @Query(nativeQuery = true)
//    List<Store> reportTopStoresPerCategory();
}
