package com.acme.team7.ACMEDeliveryService.bootstrap;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SampleContent extends BaseComponent implements CommandLineRunner {
    private final AccountService accountService;
    private final OrderService orderService;
    private final ProductService productService;
    private final StoreService storeService;
    private final StoreCategoryService storeCategoryService;

    @Override
    public void run(String... args) throws Exception {

        //Implement below Accounts


        StoreCategory tavern = storeCategoryService.create(StoreCategory.builder().description("Tavern").build());
        StoreCategory coffeeShop = storeCategoryService.create(StoreCategory.builder().description("Coffee Shop").build());
        StoreCategory iceCreamShop = storeCategoryService.create(StoreCategory.builder().description("Ice Cream Shop").build());
        StoreCategory souvlatzidiko = storeCategoryService.create(StoreCategory.builder().description("Souvlatzidiko").build());
        storeCategoryService.findAll().forEach(sc -> log.info("Store category {} created.", sc));

        //functionaliti na min mporei na paragilei apo makria??
        List<Store> storeList = List.of(
                Store.builder().name("Louloudaki").address("Plateia Eleftheris 51, 71303 Athens Attiki").storeCategory(tavern).build(),
                Store.builder().name("Xelona").address("Analipsi 7, 70303 Athens Attiki").storeCategory(tavern).build(),
                Store.builder().name("Kagiampis").address("Plateia Kornarou 51, 71203 Athens Attiki").storeCategory(souvlatzidiko).build(),
                Store.builder().name("Louloudaki").address("Dikaiosinis 51, 70201 Athens Attiki").storeCategory(iceCreamShop).build());
        log.info("Created stores. {}",storeService.createAll(storeList));

        //Implement below product category

        //Implement below products

        //Set products to stores

        accountService.findAll().forEach(c -> log.info("{}", c));

        log.info("Does customer exist? {}.", (accountService.findByEmail("c.giannacoulis@codehub.gr") != null));
        log.info("Does customer exist? {}.", (accountService.findByEmail("non-existing@gmail.com") != null));

        // Load customer and create an order by adding/updating/removing content before checking it out
        Account firstAccount = accountService.findByEmail("c.giannacoulis@codehub.gr");
        Order firstOrder = orderService.initiateOrder(firstAccount);
//find by name and by store??
        orderService.addItem(firstOrder, productService.findByName("Louloudaki Salad"), 2);
        orderService.addItem(firstOrder, productService.findByName("Livers"), 1); //findByNameAndStore_Id ?????
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
