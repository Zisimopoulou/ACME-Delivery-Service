package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.BaseModel;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiError;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import org.slf4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
public abstract class BaseControllerImpl<T extends BaseModel> extends BaseComponent {
    public Logger logger;

    protected abstract BaseService<T> getBaseService();

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> get(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(ApiResponse.<T>builder().data(getBaseService().get(id)).build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<T>>> findAll() {
        return ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getBaseService().findAll()).build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<T>> create(@Valid @RequestBody final T entity) {
        return new ResponseEntity<>(ApiResponse.<T>builder().data(getBaseService().create(entity)).build(),
                HttpStatus.CREATED);
    }

    @PostMapping(headers = "content=list")
    public ResponseEntity<ApiResponse<List<T>>> createAll(@RequestBody List<T> entities) {
        return new ResponseEntity<>(ApiResponse.<List<T>>builder().data(getBaseService().createAll(entities)).build(),
                HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody final T entity) { getBaseService().update(entity); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        getBaseService().deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @RequestBody final T entity) {
        if (getBaseService().exists(entity)) {
            getBaseService().delete(entity);
        }
    }

    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ApiResponse<Void>> notFoundException(NoSuchElementException ex, WebRequest webRequest) {
        logger.error("NoSuchElementException Exception caught");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<Void>builder().apiError(ApiError.builder().description(ex.getMessage()).httpStatus(
                        HttpStatus.NOT_FOUND.value()).path(webRequest.getDescription(false)).build()).build());
    }
}