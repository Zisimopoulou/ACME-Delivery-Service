package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Product;
import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.domain.StoreCategory;
import com.acme.team7.ACMEDeliveryService.domain.StoreProduct;
import com.acme.team7.ACMEDeliveryService.transfer.KeyTwoValues;
import com.acme.team7.ACMEDeliveryService.transfer.KeyValue;

import java.math.BigDecimal;
import java.util.List;

public interface StoreService extends BaseService<Store>{
    List<Store> findStoresByNameOrStoreCategory_Description(String name, String description);

    Store initiateStore(String name, StoreCategory storeCategory, String address);

    void addStoreProduct(Store store, Product product, String name, String details, BigDecimal price, String image);

    void updateStoreProductPrice(Store store, StoreProduct storeProduct, BigDecimal price);

    StoreProduct getStoreProduct(Store store, Long id);

    void removeStoreProduct(Store store, StoreProduct storeProduct);

    List<KeyValue<String, Long>> reportTop10StoreProducts();

    List<KeyValue<String, Long>> reportTopStores();

    List<KeyValue<String, Long>> reportTopStoresPerCategory(Long id);

    List<KeyTwoValues<String, String, String>> getLazyStores();
}
