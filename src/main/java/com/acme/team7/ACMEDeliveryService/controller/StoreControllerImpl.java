package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.StoreService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import com.acme.team7.ACMEDeliveryService.transfer.TopReports;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("STORES")
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
    public ResponseEntity<ApiResponse<StoreProduct>> getStoreProduct(Store store, Long id){
        return new ResponseEntity<>(ApiResponse.<StoreProduct>builder().
                data(storeService.getStoreProduct(store, id)).build(), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("reportTop10StoreProducts")
    public ResponseEntity<ApiResponse<List<TopReports>>> reportTop10StoreProducts() {
        return null;
    }

    @Override
    @GetMapping("reportTopStores")
    public ResponseEntity<ApiResponse<List<TopReports>>> reportTopStores() {
        return null;
    }

}