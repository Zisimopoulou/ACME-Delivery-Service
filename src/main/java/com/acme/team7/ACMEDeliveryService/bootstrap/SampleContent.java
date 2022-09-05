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

        Product xoriatiki = productService.create(Product.builder().name("xoriatiki").productcategory(ProductCategory.SALAD).build());
        Product liver = productService.create(Product.builder().name("liver").productcategory(ProductCategory.APPETISERS).build());
        Product zucchini = productService.create(Product.builder().name("zucchini").productcategory(ProductCategory.APPETISERS).build());
        Product redWine = productService.create(Product.builder().name("redWine").productcategory(ProductCategory.DRINKS).build());
        Product souvlakiKotopoulo = productService.create(Product.builder().name("souvlakiKotopoulo").productcategory(ProductCategory.SOUVLAKI).build());
        Product vanilliaFlavor = productService.create(Product.builder().name("vanilliaFlavor").productcategory(ProductCategory.ICECREAM).build());
        Product chocolateFlavor = productService.create(Product.builder().name("chocolateFlavor").productcategory(ProductCategory.ICECREAM).build());
        Product greekCoffee = productService.create(Product.builder().name("greekCoffee").productcategory(ProductCategory.DRINKS).build());
        Product papoutsakia = productService.create(Product.builder().name("papoutsakia").productcategory(ProductCategory.OVEN).build());
        Product paidakia = productService.create(Product.builder().name("tzatziki").productcategory(ProductCategory.ROASTMEAT).build());
        Product americano = productService.create(Product.builder().name("americano").productcategory(ProductCategory.DRINKS).build());
        Product orangeJuice = productService.create(Product.builder().name("orangeJuice").productcategory(ProductCategory.DRINKS).build());
        Product tzatziki = productService.create(Product.builder().name("tzatziki").productcategory(ProductCategory.SAUCE).build());

        StoreCategory tavern = storeCategoryService.create(StoreCategory.builder().description("Tavern").build());
        StoreCategory coffeeShop = storeCategoryService.create(StoreCategory.builder().description("Coffee Shop").build());
        StoreCategory iceCreamShop = storeCategoryService.create(StoreCategory.builder().description("Ice Cream Shop").build());
        StoreCategory souvlatzidiko = storeCategoryService.create(StoreCategory.builder().description("Souvlatzidiko").build());
        storeCategoryService.findAll().forEach(sc -> log.info("Store category {} created.", sc));

//        Store louloudaki = storeService.create(Store.builder().name("Louloudaki").storeCategory(tavern).address("Plateia Eleftheris 51 71303 Athens Attica").build());
//        Store xelona = storeService.create(Store.builder().name("Xelona").storeCategory(tavern).address("Analipsi 7 70303 Athens").build());
//        Store kagiampis = storeService.create(Store.builder().name("Kagiampis").storeCategory(tavern).address("Plateia Kornarou 51 71203 Athens Attica").build());
//        Store pratos = storeService.create(Store.builder().name("Pratos").storeCategory(iceCreamShop).address("Dikaiosinis 51 70201 Athens Attica").build());

        Store louloudaki = storeService.initiateStore("Louloudaki",tavern,"Plateia Eleftheris 51 71303 Athens Attica");
        louloudaki.setImage("path to flower image");
        storeService.addStoreProduct(louloudaki,xoriatiki,"Louloudaki Salad","With vinegar sauce and manouri cheese", BigDecimal.valueOf(4.5),"");
        storeService.addStoreProduct(louloudaki,liver,"Fried Liver","empty", BigDecimal.valueOf(5),"");
        storeService.addStoreProduct(louloudaki,zucchini,"Fried Zucchini","empty", BigDecimal.valueOf(3),"");
        storeService.addStoreProduct(louloudaki,redWine,"Red wine","250 ml", BigDecimal.valueOf(2),"");
        storeService.create(louloudaki);

        PaymentCard.builder().build();

//                StoreProduct.builder().name("Souvlaki kotopoulo").price(BigDecimal.valueOf(4))
//                        .product(souvlakiKotopoulo).store(louloudaki).build(),
//                StoreProduct.builder().name("Pratos vanillia ice cream").price(BigDecimal.valueOf(2.5))
//                        .product(vanilliaFlavor).store(pratos).build(),
//                StoreProduct.builder().name("Pratos chocolate ice cream").price(BigDecimal.valueOf(2.5))
//                        .product(chocolateFlavor).store(pratos).build(),
//                StoreProduct.builder().name("Tzatziki").price(BigDecimal.valueOf(4.5))
//                        .product(tzatziki).store(xelona).build(),
//                StoreProduct.builder().name("Greek coffee").price(BigDecimal.valueOf(2.5))
//                        .product(greekCoffee).store(xelona).build(),
//                StoreProduct.builder().name("Red Wine").price(BigDecimal.valueOf(2.5))
//                        .product(redWine).store(xelona).build(),
//                StoreProduct.builder().name("Papoutsakia").price(BigDecimal.valueOf(2.5))
//                        .product(papoutsakia).store(xelona).build(),
//                StoreProduct.builder().name("Paidakia").price(BigDecimal.valueOf(2.5))
//                        .product(paidakia).store(xelona).build(),
//                StoreProduct.builder().name("Greek Coffee").price(BigDecimal.valueOf(2.5))
//                        .product(greekCoffee).store(kagiampis).build(),
//                StoreProduct.builder().name("Americano").price(BigDecimal.valueOf(2.5))
//                        .product(americano).store(kagiampis).build(),
//                StoreProduct.builder().name("Orange juice").price(BigDecimal.valueOf(2.5))
//                        .product(orangeJuice).store(kagiampis).build());

        Account firstAccount = accountService.findByEmail("mommykmr@hotmail.red");
        Order firstOrder = orderService.initiateOrder(firstAccount);
        firstOrder.setSerial("0000010001");

        orderService.addItem(firstOrder, storeService.getStoreProduct(louloudaki,4L), 2);

//        // Update item(s)
//        orderService.addItem(firstOrder, productService.findBySerial("30-0-00000-1-0002"), 1);
//        orderService.updateItem(firstOrder, productService.findBySerial("30-0-00000-1-0002"), 2);
//        // Remove item(s)
//        orderService.removeItem(firstOrder, productService.findBySerial("30-0-00000-1-0003"));

        orderService.proceedToCheckout(firstOrder,PaymentMethod.CASH);
        orderService.checkout(firstOrder);
//
//        Account secondCustomer = accountService.get(2L);
//        Order secondOrder = orderService.initiateOrder(secondCustomer);
//        secondOrder.setSerial("0000010002");
//
//        orderService.addItem(secondOrder, productService.findBySerial("30-0-00000-2-0001"), 1);
//        orderService.addItem(secondOrder, productService.findBySerial("30-0-00000-2-0002"), 1);
//        orderService.proceedToCheckout(secondOrder,PaymentMethod.CASH);
//        orderService.checkout(secondOrder);
//
//        Account thirdAccount = accountService.findByPhoneNumber("6984072273");
//        Order thirdOrder = orderService.initiateOrder(thirdAccount);
//        thirdOrder.setSerial("0000010003");
//
//        orderService.addItem(thirdOrder, productService.findBySerial("30-0-00000-3-0001"), 3);
//        orderService.addItem(thirdOrder, productService.findBySerial("30-0-00000-3-0002"), 2);
//        orderService.addItem(thirdOrder, productService.findBySerial("30-0-00000-3-0003"), 1);
//        orderService.proceedToCheckout(thirdOrder,PaymentMethod.PAYMENT_CARD);
//        orderService.checkout(thirdOrder);

    }
}
