package com.acme.team7.ACMEDeliveryService.controller;
import com.acme.team7.ACMEDeliveryService.domain.Product;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.service.ProductService;
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
@RequestMapping("Products")

public class ProductControllerImpl extends BaseControllerImpl<Product> implements ProductController{
    private final ProductService productService;
    @Override
    public BaseService<Product> getBaseService() {
        return productService;
    }
    @GetMapping(params = "Name")
    public ResponseEntity<ApiResponse<Product>> findProductByName(@RequestParam String name) {
        final Product Byname = productService.findByName(name);
        if (Byname== null) {
            throw new NoSuchElementException("Name not found");
        }
        return  ResponseEntity.ok(ApiResponse.<Product>builder().data(Byname).build());
    }

}
