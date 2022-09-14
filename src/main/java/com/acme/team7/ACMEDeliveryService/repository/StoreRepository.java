package com.acme.team7.ACMEDeliveryService.repository;

import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.transfer.KeyValue;
import com.acme.team7.ACMEDeliveryService.transfer.TopReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByNameOrStoreCategory_Description(String name, String description);

    Store findStoreById(Long id);

    @Query(name = "Store.ReportTop10StoreProducts",nativeQuery = true)
    List<KeyValue<String, Long>> reportTop10StoreProducts();

    @Query(name = "Store.ReportTopStores",nativeQuery = true)
    List<KeyValue<String, Long>> reportTopStores();

    @Query(name = "Store.ReportTopStoresPerCategory",nativeQuery = true)
    List<KeyValue<String, Long>> reportTopStoresPerCategory(Long id);
}
