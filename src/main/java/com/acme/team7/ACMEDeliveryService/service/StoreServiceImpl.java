package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.repository.StoreRepository;
import com.acme.team7.ACMEDeliveryService.transfer.KeyTwoValues;
import com.acme.team7.ACMEDeliveryService.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService{
    private final StoreRepository storeRepository;

    @Override
    public JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }

    @Override
    public List<Store> findStoresByNameOrStoreCategory_Description(final String name, final String description) {
        return storeRepository.findStoresByNameOrStoreCategory_Description(name,description);
    }

    @Override
    public Store initiateStore(String name, StoreCategory storeCategory, String address) {
        return Store.builder().name(name).storeCategory(storeCategory).address(address).storeProducts(new HashSet<>()).build();
    }

    @Override
    public void addStoreProduct(Store store, ProductCategory product, String name, String details, BigDecimal price, String image) {
        store.getStoreProducts().add(storeProductCreation(store,product, name, details, price, image));
        logger.info("Store products added to the store.");
    }

    @Override
    public StoreProduct getStoreProduct(Store store, Long id){
        Iterator<StoreProduct> storeProductIterator = store.getStoreProducts().iterator();
        List<StoreProduct> storeProducts = new ArrayList<>();
        while(storeProductIterator.hasNext()) {
            storeProducts.add(storeProductIterator.next());
        }
        for (StoreProduct storeProduct: storeProducts) {
            if (storeProduct.getId().equals(id)) {
                return storeProduct;
            }
        }
        logger.info("Unable to find store product with id {}.", id);
        return null;
    }

    private StoreProduct storeProductCreation(Store store, ProductCategory productCategory, String name, String details, BigDecimal price, String image) {
        return StoreProduct.builder().store(store).productCategory(productCategory).name(name).details(details).price(price).image(image).build();
    }

    @Override
    public List<KeyValue<String, Long>> reportTop10StoreProducts() {
        return storeRepository.reportTop10StoreProducts();
    }
    @Override
    public List<KeyTwoValues<String, String, String>> reportTopStores() {
        return storeRepository.reportTopStores();
    }

    @Override
    public List<KeyValue<String, Long>> reportTopStoresPerCategory(final Long id) {
        return storeRepository.reportTopStoresPerCategory(id);
    }

    @Override
    public List<KeyTwoValues<String, String, String>> getLazyStores() {
        return storeRepository.getLazyStores();
    }
}
