package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.BaseModel;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface BaseController<T extends BaseModel> {

    ResponseEntity<ApiResponse<T>> get(@PathVariable("id") final Long id);
    ResponseEntity<ApiResponse<List<T>>> findAll();

    ResponseEntity<ApiResponse<T>> create(@Valid @RequestBody final T entity);

    ResponseEntity<ApiResponse<List<T>>> createAll(@RequestBody List<T> entities);

    void update(@Valid @RequestBody final T entity);

    void delete(@PathVariable("id") final Long id);

    void delete(@Valid @RequestBody final T entity);
}
