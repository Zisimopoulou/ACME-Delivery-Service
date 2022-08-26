package com.acme.team7.ACMEDeliveryService.bootstrap;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.service.AccountService;
import com.acme.team7.ACMEDeliveryService.service.OrderService;
import com.acme.team7.ACMEDeliveryService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleContent extends BaseComponent implements CommandLineRunner {
    private final AccountService customerService;
    private final OrderService orderService;
    private final ProductService productService;


    @Override
    public void run(String... args) throws Exception {

    }
}
