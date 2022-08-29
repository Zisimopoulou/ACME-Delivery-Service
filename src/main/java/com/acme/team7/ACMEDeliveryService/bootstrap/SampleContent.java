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
    private final ProductCategoryService productCategoryService;

    @Override
    public void run(String... args) throws Exception {

        //Implement below Accounts
        List<Account> accountList= accountService.createAll(
                Account.builder().firstName("Apostolis").lastName("Antoniadis")
                        .address("Eleftheriou Venizelou 7 15341 Athina Attica").email("akhvakhec@greendike.com")
                        .password("44&4C&b7Z20^").phoneNumber("6984072273").age(25).build(),
                Account.builder().firstName("Ermis").lastName("Valides")
                        .address("Patriarchou Ioakim 45 10676 Athina Attica").email("mommykmr@hotmail.red")
                        .password("hRF2Uc740&^8").phoneNumber("6998438152").age(46).build(),
                Account.builder().firstName("Panikos").lastName("Demetriidis")
                        .address("Sofokleous 26 10552 Athina Attica").email("neeneemusic@schule-breklum.de")
                        .password("Bc%89AVSw1jw").phoneNumber("6971078143").age(32).build(),
                Account.builder().firstName("Nikolas").lastName("Tassides")
                        .address("Rovertou Galli 4 11742 Athina Attica").email("lev12@typestring.com")
                        .password("We9#8uzEl8lF").phoneNumber("6948361237").age(19).build());
                log.info("Accounts created: {}.", accountService.createAll(accountList));

        //Store Category
        StoreCategory tavern = storeCategoryService.create(StoreCategory.builder().description("Tavern").build());
        StoreCategory coffeeShop = storeCategoryService.create(StoreCategory.builder().description("Coffee Shop").build());
        StoreCategory iceCreamShop = storeCategoryService.create(StoreCategory.builder().description("Ice Cream Shop").build());
        StoreCategory souvlatzidiko = storeCategoryService.create(StoreCategory.builder().description("Souvlatzidiko").build());
        storeCategoryService.findAll().forEach(sc -> log.info("Store category {} created.", sc));

        //functionaliti na min mporei na paragilei apo makria??
        List<Store> storeList = List.of(
                Store.builder().name("Louloudaki").address("Plateia Eleftheris 51 71303 Athens Attiki").storeCategory(tavern).build(),
                Store.builder().name("Xelona").address("Analipsi 7 70303 Athens Attiki").storeCategory(tavern).build(),
                Store.builder().name("Kagiampis").address("Plateia Kornarou 51 71203 Athens Attiki").storeCategory(souvlatzidiko).build(),
                Store.builder().name("Pratos").address("Dikaiosinis 51 70201 Athens Attiki").storeCategory(iceCreamShop).build());
        log.info("Created stores. {}",storeService.createAll(storeList));

        //Implement below product category
        StoreCategory coffee = storeCategoryService.create(StoreCategory.builder().description("Coffeee").build());
        //Implement below products

        //Set products to stores

        accountService.findAll().forEach(c -> log.info("{}", c));

        log.info("Does customer email exist? {}.", (accountService.findByEmail("neeneemusic@schule-breklum.de") != null));
        log.info("Does customer email exist? {}.", (accountService.findByEmail("mommykmr@hotmail.red") != null));

        // Load customer and create an order by adding/updating/removing content before checking it out
        Account firstAccount = accountService.findByEmail("mommykmr@hotmail.red");
        Order firstOrder = orderService.initiateOrder(firstAccount);
//find by name and by store??
        orderService.addItem(firstOrder, productService.findByName("Louloudaki Salad"), 2); //store=Louloudaki
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

        orderService.addItem(secondOrder, productService.findByName("Ice cream vanillia"), 1);//store=Pratos
        orderService.addItem(secondOrder, productService.findByName("Ice cream chocolate"), 1);
        orderService.checkout(secondOrder, PaymentMethod.CASH);

        // Third customer and order
        Account thirdAccount = accountService.findByPhoneNumber("6984072273");
        Order thirdOrder = orderService.initiateOrder(thirdAccount);
        orderService.addItem(thirdOrder, productService.findByName("Tzatziki"), 3); //store=Kagiampis
        orderService.addItem(thirdOrder, productService.findByName("Greek cofee"), 2);
        orderService.addItem(thirdOrder, productService.findByName("Fried potatos"), 1);
        orderService.checkout(thirdOrder, PaymentMethod.CREDIT_CARD);

    }
}
