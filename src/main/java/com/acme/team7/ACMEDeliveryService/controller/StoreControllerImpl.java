package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.StoreService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import com.acme.team7.ACMEDeliveryService.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("STORES")
@CrossOrigin
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
    @GetMapping("reportTop10StoreProducts")
    public ResponseEntity<ApiResponse<List<KeyValue<String, Long>>>> reportTop10StoreProducts() {
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, Long>>>builder().data(storeService.reportTop10StoreProducts()).build());
    }

    @Override
    @GetMapping("reportTopStores")
    public ResponseEntity<ApiResponse<List<KeyValue<String, Long>>>> reportTopStores() {
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, Long>>>builder().data(storeService.reportTopStores()).build());
    }

}