package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.StoreCategory;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.StoreCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("storecategories")
public class StoreCategoryControllerImpl extends BaseControllerImpl<StoreCategory> implements StoreCategoryController {
    private final StoreCategoryService storeCategoryService;
    @Override
    public BaseService<StoreCategory> getBaseService() {
        return storeCategoryService;
    }
}
