package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.StoreCategory;
import com.acme.team7.ACMEDeliveryService.repository.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCategoryServiceImpl extends BaseServiceImpl<StoreCategory> implements StoreCategoryService{
    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    public JpaRepository<StoreCategory, Long> getRepository() {
        return storeCategoryRepository;
    }
}
