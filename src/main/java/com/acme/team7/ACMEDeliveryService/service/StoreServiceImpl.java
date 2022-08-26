package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService{
    private final StoreRepository storeRepository;

    @Override
    public JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }

    @Override
    public Store findByName(final String name) {
        return storeRepository.findStoreByName(name);
    }

    @Override
    public List<Store> findStoreByStoreCategory_Description(final String description) {
        return storeRepository.findStoreByStoreCategory_Description(description);
    }

//    @Override
//    public List<Store> reportTopStores() {
//        return storeRepository.reportTopStores();
//    }
//
//    @Override
//    public List<Store> reportTopStoresPerCategory() {
//        return storeRepository.reportTopStoresPerCategory();
//    }
}
