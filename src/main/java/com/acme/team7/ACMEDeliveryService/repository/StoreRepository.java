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
            value = "  SELECT STOREPRODUCT_ID, STOREPRODUCT.NAME as nameOrId,COUNT(STOREPRODUCT_ID) frequency \n" +
                    "  FROM ORDER_ITEMS \n" +
                    "  INNER JOIN STOREPRODUCT ON STOREPRODUCT.ID=ORDER_ITEMS.STOREPRODUCT_ID \n" +
                    "  GROUP BY STOREPRODUCT_ID, STOREPRODUCT.NAME \n" +
                    "  ORDER BY COUNT(STOREPRODUCT_ID) DESC \n" +
                    "  FETCH NEXT 10 ROWS ONLY",
            nativeQuery = true)
    List<TopStoreProducts> reportTop10StoreProducts();

    @Query(
            value = "  SELECT store_id as nameOrId, COUNT(store_id) as frequency \n" +
                    "  FROM (\n" +
                    "  SELECT order_id, store_id \n" +
                    "  FROM ORDER_ITEMS \n" +
                    "  INNER JOIN STOREPRODUCT ON storeproduct.id=order_items.storeproduct_id \n" +
                    "  Group by order_id, store_id \n" +
                    "  ) \n" +
                    "  GROUP BY store_id \n" +
                    "  ORDER BY frequency DESC \n" +
                    "  FETCH NEXT 20 ROWS ONLY",
            nativeQuery = true)
    List<TopStoreProducts> reportTopStores();

//    @Query(nativeQuery = true)
//    List<Store> reportTopStoresPerCategory();
}
