package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.ProductCategory;
import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.domain.StoreCategory;
import com.acme.team7.ACMEDeliveryService.domain.StoreProduct;
import com.acme.team7.ACMEDeliveryService.transfer.KeyThreeValues;
import com.acme.team7.ACMEDeliveryService.transfer.KeyTwoValues;

import java.math.BigDecimal;
import java.util.List;

public interface StoreService extends BaseService<Store>{
    List<Store> findStoresByName(String name);

    List<Store> findStoresByStoreCategory_Description(String description);

    List<Store> findStoresByNameOrStoreCategory_Description(String nameOrCategory);

    Store initiateStore(String name, StoreCategory storeCategory, String address);

    void addStoreProduct(Store store, ProductCategory product, String name, String details, BigDecimal price, String image);

    StoreProduct getStoreProduct(Store store, Long id);

    List<KeyThreeValues<String,String,String,String>> reportTop10StoreProducts();

    List<KeyTwoValues<String, String, String>> reportTopStores();

    List<KeyTwoValues<String,String,String>> reportTopStoresPerCategory(Long id);

    List<KeyTwoValues<String, String, String>> getLazyStores();
}
