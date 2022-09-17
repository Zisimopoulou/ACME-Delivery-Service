package com.acme.team7.ACMEDeliveryService.controller;
import com.acme.team7.ACMEDeliveryService.domain.ProductCategory;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.ProductCategoryService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
@RestController
@RequiredArgsConstructor
@RequestMapping("productcategories")

public class ProductCategoryControllerImpl extends BaseControllerImpl<ProductCategory> {
    private final ProductCategoryService productService;
    @Override
    public BaseService<ProductCategory> getBaseService() {
        return productService;
    }
}
