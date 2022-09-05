package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.Store;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.StoreService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

//    @Override
//    public ResponseEntity<ApiResponse<List<Store>>> findByName(@RequestParam String name) {
//        final List<Store> stores = storeService.findByName(name);
//        //prepei na exoume xexoristo exception gia kathe classi?
//        if (name == null) {
//            throw new NoSuchElementException("Unable to find a store with null name.");
//        }
//        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(stores).build());
//    }
//
//    @Override
//    public ResponseEntity<ApiResponse<List<Store>>> findStoreByStoreCategory_Description(String description) {
//        final List<Store> stores = storeService.findByName(description);
//        //prepei na exoume xexoristo exception gia kathe classi?
//        if (description == null) {
//            throw new NoSuchElementException("Unable to find a store with null description.");
//        }
//        return ResponseEntity.ok(ApiResponse.<List<Store>>builder().data(stores).build());
//    }


}