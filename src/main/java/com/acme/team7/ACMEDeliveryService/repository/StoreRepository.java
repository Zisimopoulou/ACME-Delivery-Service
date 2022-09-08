package com.acme.team7.ACMEDeliveryService.repository;

import com.acme.team7.ACMEDeliveryService.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByNameOrStoreCategory_Description(String name, String description);

    Store findStoreById(Long id);

    @Query(nativeQuery = true)
    List<Store> reportTopStores();

    @Query(nativeQuery = true)
    List<Store> reportTopStoresPerCategory();
}
