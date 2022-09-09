package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import com.acme.team7.ACMEDeliveryService.transfer.TopReports;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface StoreController {
  ResponseEntity<ApiResponse<List<Store>>> findStoreByStoreCategory_Description(String name, String description);
  ResponseEntity<ApiResponse<StoreProduct>> getStoreProduct(Store store, Long id);
  ResponseEntity<ApiResponse<List<TopReports>>> reportTop10StoreProducts();
  ResponseEntity<ApiResponse<List<TopReports>>> reportTopStores();
}
