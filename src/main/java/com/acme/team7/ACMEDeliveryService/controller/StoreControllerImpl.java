package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.StoreService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import com.acme.team7.ACMEDeliveryService.transfer.KeyTwoValues;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("stores")
@CrossOrigin
public class StoreControllerImpl extends BaseControllerImpl<Store> {

    private final StoreService storeService;

    @Override
    public BaseService<Store> getBaseService() {
        return storeService;
    }

    @GetMapping(value = "name-category/{nameOrCategory}")
    public ResponseEntity<ApiResponse<List<Store>>> findStoresByNameOrStoreCategory_Description(@PathVariable("nameOrCategory") String nameOrCategory) {
        final List<Store> stores = storeService.findStoresByNameOrStoreCategory_Description(nameOrCategory);
        if (nameOrCategory == null) {
            throw new NoSuchElementException("Unable to find a store with null description.");
        }
        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(stores).build());
    }
    @GetMapping("reportTop10StoreProducts")
    public ResponseEntity<ApiResponse<List<KeyTwoValues<String,String,String>>>> reportTop10StoreProducts() {
        return ResponseEntity.ok(ApiResponse.<List<KeyTwoValues<String,String,String>>>builder().data(storeService.reportTop10StoreProducts()).build());
    }

    @GetMapping("reportTopStores")
    public ResponseEntity<ApiResponse<List<KeyTwoValues<String, String, String>>>> reportTopStores() {
        return ResponseEntity.ok(ApiResponse.<List<KeyTwoValues<String, String, String>>>builder().data(storeService.reportTopStores()).build());
    }

    @GetMapping("reportTopStoresPerCategory/{id}")
    public ResponseEntity<ApiResponse<List<KeyTwoValues<String,String,String>>>> reportTopStoresPerCategory(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(ApiResponse.<List<KeyTwoValues<String,String,String>>>builder().data(storeService.reportTopStoresPerCategory(id)).build());
    }

    @GetMapping("lazy")
    public ResponseEntity<ApiResponse<List<KeyTwoValues<String, String, String>>>> findLazyStores() {
        return ResponseEntity.ok(ApiResponse.<List<KeyTwoValues<String, String, String>>>builder().data(storeService.getLazyStores()).build());
    }

}