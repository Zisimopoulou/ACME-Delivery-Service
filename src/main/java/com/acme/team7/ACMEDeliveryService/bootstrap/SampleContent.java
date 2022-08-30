package com.acme.team7.ACMEDeliveryService.bootstrap;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
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

        List<Account> accountList= accountService.createAll(
                Account.builder().firstName("Apostolis").lastName("Antoniadis")
                        .address("Eleftheriou Venizelou 7 15341 Athina Attica").email("akhvakhec@greendike.com")
                        .password("44&4C&b7Z20").phoneNumber("6984072273").age(25).build(),
                Account.builder().firstName("Ermis").lastName("Valides")
                        .address("Patriarchou Ioakim 45 10676 Athina Attica").email("mommykmr@hotmail.red")
                        .password("44&4C&b7Z21").phoneNumber("6998438152").age(46).build(),
                Account.builder().firstName("Panikos").lastName("Demetriidis")
                        .address("Sofokleous 26 10552 Athina Attica").email("neeneemusic@schule-breklum.de")
                        .password("44&4C&b7Z22").phoneNumber("6971078143").age(32).build(),
                Account.builder().firstName("Nikolas").lastName("Tassides")
                        .address("Rovertou Galli 4 11742 Athina Attica").email("lev12@typestring.com")
                        .password("44&4C&b7Z23").phoneNumber("6948361237").age(19).build());
                log.info("Accounts created: {}.", accountService.createAll(accountList));

        accountService.findAll().forEach(c -> log.info("{}", c));

        log.info("Does customer email exist? {}.", (accountService.findByEmail("neeneemusic@schule-breklum.de") != null));
        log.info("Does customer email exist? {}.", (accountService.findByEmail("mommykmr@hotmail.red") != null));

        StoreCategory tavern = storeCategoryService.create(StoreCategory.builder().description("Tavern").build());
        StoreCategory coffeeShop = storeCategoryService.create(StoreCategory.builder().description("Coffee Shop").build());
        StoreCategory iceCreamShop = storeCategoryService.create(StoreCategory.builder().description("Ice Cream Shop").build());
        StoreCategory souvlatzidiko = storeCategoryService.create(StoreCategory.builder().description("Souvlatzidiko").build());
        storeCategoryService.findAll().forEach(sc -> log.info("Store category {} created.", sc));

        Store louloudaki = storeService.create(Store.builder().name("Louloudaki").storeCategory(tavern).address("Plateia Eleftheris 51 71303 Athens Attica").build());
        Store xelona = storeService.create(Store.builder().name("Xelona").storeCategory(tavern).address("Analipsi 7 70303 Athens").build());
        Store kagiampis = storeService.create(Store.builder().name("Kagiampis").storeCategory(tavern).address("Plateia Kornarou 51 71203 Athens Attica").build());
        Store pratos = storeService.create(Store.builder().name("Pratos").storeCategory(iceCreamShop).address("Dikaiosinis 51 70201 Athens Attica").build());
        storeCategoryService.findAll().forEach(s -> log.info("Stores {} created.", s));

        ProductCategory salad = productCategoryService.create(ProductCategory.builder().description("Salad").build());
        ProductCategory sideDish = productCategoryService.create(ProductCategory.builder().description("Side Dish").build());
        ProductCategory souvlaki = productCategoryService.create(ProductCategory.builder().description("Souvlaki").build());
        ProductCategory iceCream = productCategoryService.create(ProductCategory.builder().description("Ice Cream").build());
        ProductCategory sauce = productCategoryService.create(ProductCategory.builder().description("Sauce").build());
        ProductCategory oven = productCategoryService.create(ProductCategory.builder().description("Oven dish").build());
        ProductCategory roastMeat = productCategoryService.create(ProductCategory.builder().description("Roast meat").build());
        ProductCategory drinks = productCategoryService.create(ProductCategory.builder().description("Drinks").build());
        storeCategoryService.findAll().forEach(pc -> log.info("Product category {} created.", pc));

        List<Product> products = List.of(Product.builder().serial("30-0-00000-1-0001").name("Louloudaki Salad").price(BigDecimal.valueOf(4))
                                                .productcategory(salad).store(louloudaki).build(),
                                        Product.builder().serial("30-0-00000-1-0002").name("Liver").price(BigDecimal.valueOf(6))
                                                .productcategory(sideDish).store(louloudaki).build(),
                                        Product.builder().serial("30-0-00000-1-0003").name("Fried Zucchini").price(BigDecimal.valueOf(3.5))
                                                .productcategory(sideDish).store(louloudaki).build(),
                                        Product.builder().serial("30-0-00000-1-0004").name("Red wine").price(BigDecimal.valueOf(4))
                                                .productcategory(drinks).store(louloudaki).build(),
                                        Product.builder().serial("30-0-00000-1-0005").name("Souvlaki kotopoulo").price(BigDecimal.valueOf(4))
                                                .productcategory(souvlaki).store(louloudaki).build(),
                                        Product.builder().serial("30-0-00000-2-0001").name("vanillia").price(BigDecimal.valueOf(2.5))
                                                .productcategory(iceCream).store(pratos).build(),
                                        Product.builder().serial("30-0-00000-2-0002").name("chocolate").price(BigDecimal.valueOf(2.5))
                                                .productcategory(iceCream).store(pratos).build(),
                                        Product.builder().serial("30-0-00000-2-0003").name("mango").price(BigDecimal.valueOf(2.5))
                                                .productcategory(iceCream).store(pratos).build(),
                                        Product.builder().serial("30-0-00000-3-0001").name("Tzatziki").price(BigDecimal.valueOf(4.5))
                                                .productcategory(sauce).store(xelona).build(),
                                        Product.builder().serial("30-0-00000-3-0002").name("Greek cofee").price(BigDecimal.valueOf(2.5))
                                                 .productcategory(drinks).store(xelona).build(),
                                        Product.builder().serial("30-0-00000-3-0003").name("Fried potatos").price(BigDecimal.valueOf(2.5))
                                                .productcategory(sideDish).store(xelona).build(),
                                        Product.builder().serial("30-0-00000-3-0004").name("Red Wine").price(BigDecimal.valueOf(2.5))
                                                .productcategory(drinks).store(xelona).build(),
                                        Product.builder().serial("30-0-00000-3-0005").name("Papoutsakia").price(BigDecimal.valueOf(2.5))
                                                .productcategory(oven).store(xelona).build(),
                                        Product.builder().serial("30-0-00000-3-0006").name("Paidakia").price(BigDecimal.valueOf(2.5))
                                                .productcategory(roastMeat).store(xelona).build(),
                                        Product.builder().serial("30-0-00000-4-0001").name("Greek Coffee").price(BigDecimal.valueOf(2.5))
                                                .productcategory(drinks).store(kagiampis).build(),
                                        Product.builder().serial("30-0-00000-4-0002").name("Americano").price(BigDecimal.valueOf(2.5))
                                                .productcategory(drinks).store(kagiampis).build(),
                                        Product.builder().serial("30-0-00000-4-0003").name("Orange juice").price(BigDecimal.valueOf(2.5))
                                                .productcategory(drinks).store(kagiampis).build());
        log.info("Accounts created: {}.", productService.createAll(products));
        System.out.println(productService.findBySerial("30-0-00000-4-0002"));

        Account firstAccount = accountService.findByEmail("mommykmr@hotmail.red");
        Order firstOrder = orderService.initiateOrder(firstAccount);
        firstOrder.setSerial("0000010001");

        orderService.addItem(firstOrder, productService.findBySerial("30-0-00000-1-0001"), 2);
        orderService.addItem(firstOrder, productService.findBySerial("30-0-00000-1-0002"), 1);
        orderService.addItem(firstOrder, productService.findBySerial("30-0-00000-1-0003"), 1);
//        // Update item(s)
//        orderService.addItem(firstOrder, productService.findBySerial("30-0-00000-1-0002"), 1);
//        orderService.updateItem(firstOrder, productService.findBySerial("30-0-00000-1-0002"), 2);
//        // Remove item(s)
//        orderService.removeItem(firstOrder, productService.findBySerial("30-0-00000-1-0003"));
        orderService.checkout(firstOrder, PaymentMethod.CREDIT_CARD);

        Account secondCustomer = accountService.get(2L);
        Order secondOrder = orderService.initiateOrder(secondCustomer);
        secondOrder.setSerial("0000010002");

        orderService.addItem(secondOrder, productService.findBySerial("30-0-00000-2-0001"), 1);
        orderService.addItem(secondOrder, productService.findBySerial("30-0-00000-2-0002"), 1);
        orderService.checkout(secondOrder, PaymentMethod.CASH);

        Account thirdAccount = accountService.findByPhoneNumber("6984072273");
        Order thirdOrder = orderService.initiateOrder(thirdAccount);
        thirdOrder.setSerial("0000010003");

        orderService.addItem(thirdOrder, productService.findBySerial("30-0-00000-3-0001"), 3);
        orderService.addItem(thirdOrder, productService.findBySerial("30-0-00000-3-0002"), 2);
        orderService.addItem(thirdOrder, productService.findBySerial("30-0-00000-3-0003"), 1);
        orderService.checkout(thirdOrder, PaymentMethod.CREDIT_CARD);

    }
}
