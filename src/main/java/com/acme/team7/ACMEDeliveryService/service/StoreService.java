package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Product;
import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.domain.StoreCategory;
import com.acme.team7.ACMEDeliveryService.domain.StoreProduct;
import com.acme.team7.ACMEDeliveryService.transfer.TopReports;

import java.math.BigDecimal;
import java.util.List;

public interface StoreService extends BaseService<Store>{
    List<Store> findStoresByNameOrStoreCategory_Description(String name, String description);

    Store initiateStore(String name, StoreCategory storeCategory, String address);

    void addStoreProduct(Store store, Product product, String name, String details, BigDecimal price, String image);

    void updateStoreProductPrice(Store store, StoreProduct storeProduct, BigDecimal price);

    StoreProduct getStoreProduct(Store store, Long id);

    void removeStoreProduct(Store store, StoreProduct storeProduct);

    List<TopReports> reportTop10StoreProducts();

    List<TopReports> reportTopStores();

    List<TopReports> reportTopStoresPerCategory(Long id);
}
