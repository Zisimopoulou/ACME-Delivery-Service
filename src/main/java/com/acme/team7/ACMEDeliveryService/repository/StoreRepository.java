package com.acme.team7.ACMEDeliveryService.repository;

import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.transfer.KeyTwoValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByName(String name);

    List<Store> findStoresByStoreCategory_Description(String description);

    @Query(name = "Store.ReportTop10StoreProducts",nativeQuery = true)
    List<KeyTwoValues<String,String,String>> reportTop10StoreProducts();

    @Query(name = "Store.ReportTopStores",nativeQuery = true)
    List<KeyTwoValues<String, String, String>> reportTopStores();

    @Query(name = "Store.ReportTopStoresPerCategory",nativeQuery = true)
    List<KeyTwoValues<String,String,String>> reportTopStoresPerCategory(Long id);

    @Query(name = "Store.GetLazyStores",nativeQuery = true)
    List<KeyTwoValues<String, String, String>> getLazyStores();
}
