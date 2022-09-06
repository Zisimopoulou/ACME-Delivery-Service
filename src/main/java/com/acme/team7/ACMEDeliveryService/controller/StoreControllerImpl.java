package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.StoreService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("stores")
public class StoreControllerImpl extends BaseControllerImpl<Store> implements StoreController {

    private final StoreService storeService;

    @Override
    public BaseService<Store> getBaseService() {
        return storeService;
    }
    @Override
    public ResponseEntity<ApiResponse<List<Store>>> findStoreByStoreCategory_Description(String name, String description) {
        final List<Store> stores = storeService.findStoresByNameOrStoreCategory_Description(name, description);
        if (description == null) {
            throw new NoSuchElementException("Unable to find a store with null description.");
        }
        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(stores).build());
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Store>> initiateStore(String name, StoreCategory storeCategory, String address) {
        return new ResponseEntity<>(ApiResponse.<Store>builder().data(storeService.initiateStore(name, storeCategory, address)).build(),
                HttpStatus.CREATED);
    }

    @Override
    public void addStoreProduct(Store store, Product product, String name, String details, BigDecimal price, String image) {
        storeService.addStoreProduct(store, product, name, details, price, image);
    }

    @Override
    public void updateStoreProductPrice(Store store, StoreProduct storeProduct, BigDecimal price) {
        storeService.updateStoreProductPrice(store, storeProduct, price);
    }

    @Override
    public void removeStoreProduct(Store store, StoreProduct storeProduct) {
        storeService.removeStoreProduct(store, storeProduct);
    }

    @Override
    public ResponseEntity<ApiResponse<StoreProduct>>  getStoreProduct(Store store, Long id){
        return new ResponseEntity<>(ApiResponse.<StoreProduct>builder().
                data(storeService.getStoreProduct(store, id)).build(), HttpStatus.CREATED);
    }

}