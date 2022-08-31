package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.BaseModel;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class BaseControllerImpl<T extends BaseModel> extends BaseComponent implements BaseController<T> {
    protected abstract BaseService<T> getBaseService();

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> get(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(ApiResponse.<T>builder().data(getBaseService().get(id)).build());
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<T>>> findAll() {
        return ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getBaseService().findAll()).build());
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<T>> create(@Valid @RequestBody final T entity) {
        return new ResponseEntity<>(ApiResponse.<T>builder().data(getBaseService().create(entity)).build(),
                HttpStatus.CREATED);
    }

    @Override
    @PostMapping(headers = "content=list")
    public ResponseEntity<ApiResponse<List<T>>> createAll(@RequestBody List<T> entities) {
        return new ResponseEntity<>(ApiResponse.<List<T>>builder().data(getBaseService().createAll(entities)).build(),
                HttpStatus.CREATED);
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody final T entity) { getBaseService().update(entity); }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        getBaseService().deleteById(id);
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @RequestBody final T entity) {
        if (getBaseService().exists(entity)) {
            getBaseService().delete(entity);
        }
    }
}