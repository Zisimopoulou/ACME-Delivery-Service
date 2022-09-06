package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface StoreController {
  ResponseEntity<ApiResponse<List<Store>>> findStoreByStoreCategory_Description(String name, String description);
  ResponseEntity<ApiResponse<Store>> initiateStore(String name, StoreCategory storeCategory, String address);
  void addStoreProduct(Store store, Product product, String name, String details, BigDecimal price, String image);
  void updateStoreProductPrice(Store store, StoreProduct storeProduct, BigDecimal price);
  ResponseEntity<ApiResponse<StoreProduct>> getStoreProduct(Store store, Long id);
  void removeStoreProduct(Store store, StoreProduct storeProduct);
}
