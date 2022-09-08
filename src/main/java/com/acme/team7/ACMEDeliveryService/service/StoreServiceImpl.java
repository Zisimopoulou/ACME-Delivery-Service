package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.repository.StoreRepository;
import com.acme.team7.ACMEDeliveryService.transfer.TopStoreProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
    public List<Store> findStoresByNameOrStoreCategory_Description(String name, String description) {
        return storeRepository.findStoresByNameOrStoreCategory_Description(name,description);
    }

    @Override
    public Store initiateStore(String name, StoreCategory storeCategory, String address) {
        return Store.builder().name(name).storeCategory(storeCategory).address(address).storeProducts(new HashSet<>()).build();
    }

    @Override
    public void addStoreProduct(Store store, Product product, String name, String details, BigDecimal price, String image) {
        store.getStoreProducts().add(storeProductCreation(store,product, name, details, price, image));
        log.info("Store products added to the store.");
    } //elegxontai ta patterns meso tou build etsi?? opote edo den xreiazetai na checkaro kati etsi?


    //kamia kali idea gia na min exo diaforetika function gia to kathe update?? add ki alla px name,details,ktlp
    @Override
    public void updateStoreProductPrice(Store store, StoreProduct storeProduct, BigDecimal price) { //add check nullability??
        store.getStoreProducts().removeIf(sp -> sp.getId().equals(storeProduct.getId()));
        store.getStoreProducts().add(storeProductCreation(store,storeProduct.getProduct(),storeProduct.getName(),storeProduct.getDetails(),price,storeProduct.getImage()));
        update(store);
    }

    @Override
    public void removeStoreProduct(Store store, StoreProduct storeProduct) {
        store.getStoreProducts().removeIf(sp -> sp.getId().equals(storeProduct.getId()));
        log.info("Unable to delete store product not existing in the store.");
        update(store); //sosto?????????
    }

    @Override
    public StoreProduct getStoreProduct(Store store, Long id){
        //iterate through a set
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
        log.info("Unable to find store product with id {}.", id);
        return null;
    }

    private StoreProduct storeProductCreation(Store store, Product product, String name, String details, BigDecimal price, String image) {
        return StoreProduct.builder().store(store).product(product).name(name).details(details).price(price).image(image).build();
    }

    @Override
    public List<TopStoreProducts> reportTop10StoreProducts() {
        return storeRepository.reportTop10StoreProducts();
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
