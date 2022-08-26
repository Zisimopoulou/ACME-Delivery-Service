package com.acme.team7.ACMEDeliveryService.bootstrap;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.domain.Order;
import com.acme.team7.ACMEDeliveryService.domain.PaymentMethod;
import com.acme.team7.ACMEDeliveryService.service.AccountService;
import com.acme.team7.ACMEDeliveryService.service.OrderService;
import com.acme.team7.ACMEDeliveryService.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SampleContent extends BaseComponent implements CommandLineRunner {
    private final AccountService accountService;
    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        accountService.findAll().forEach(c -> log.info("{}", c));

        log.info("Does customer exist? {}.", (accountService.findByEmail("c.giannacoulis@codehub.gr") != null));
        log.info("Does customer exist? {}.", (accountService.findByEmail("non-existing@gmail.com") != null));

        // Load customer and create an order by adding/updating/removing content before checking it out
        Account firstAccount = accountService.findByEmail("c.giannacoulis@codehub.gr");
        Order firstOrder = orderService.initiateOrder(firstAccount);

        orderService.addItem(firstOrder, productService.findByName("Louloudaki Salad"), 2);
        orderService.addItem(firstOrder, productService.findByName("Livers"), 1);
        orderService.addItem(firstOrder, productService.findByName("Fried Zucchini"), 1);
//        // Update item(s)
//        orderService.addItem(firstOrder, productService.findByName("Livers"), 1);
//        orderService.updateItem(firstOrder, productService.findByName("Livers"), 2);
//        // Remove item(s)
//        orderService.removeItem(firstOrder, productService.findByName("Fried Zucchini"));
        orderService.checkout(firstOrder, PaymentMethod.CREDIT_CARD);

        // Second customer and order
        Account secondCustomer = accountService.get(2L);
        Order secondOrder = orderService.initiateOrder(secondCustomer);

        orderService.addItem(secondOrder, productService.findByName("Ice cream vanillia"), 1);
        orderService.addItem(secondOrder, productService.findByName("Ice cream chocolate"), 1);
        orderService.checkout(secondOrder, PaymentMethod.CASH);

        // Third customer and order
        Account thirdAccount = accountService.findByPhone("6980614522");
        Order thirdOrder = orderService.initiateOrder(thirdAccount);
        orderService.addItem(thirdOrder, productService.findByName("Tzatziki"), 3);
        orderService.addItem(thirdOrder, productService.findByName("Greek cofee"), 2);
        orderService.addItem(thirdOrder, productService.findByName("Fried potatos"), 1);
        orderService.checkout(thirdOrder, PaymentMethod.CREDIT_CARD);

    }
}
