package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.domain.StoreCategory;

import java.util.List;

public interface StoreService extends BaseService<Store>{
    Store findByName(String name);

    List<Store> findStoreByStoreCategory_Description(String description);

    List<Store> reportTopStores();

    List<Store> reportTopStoresPerCategory();
}
