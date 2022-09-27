package com.acme.team7.ACMEDeliveryService.bootstrap;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class SampleContent extends BaseComponent implements CommandLineRunner {
    private final AccountService accountService;
    private final OrderService orderService;
    private final ProductCategoryService productCategoryService;
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
        logger.info("Accounts created: {}.", accountService.createAll(accountList));

        accountService.findAll().forEach(c -> logger.info("{}", c));

        logger.info("Does customer email exist? {}.", (accountService.findByEmail("neeneemusic@schule-breklum.de") != null));
        logger.info("Does customer email exist? {}.", (accountService.findByEmail("mommykmr@hotmail.red") != null));

        ProductCategory salad = productCategoryService.create(ProductCategory.builder().description("Salad").build());
        ProductCategory noodles = productCategoryService.create(ProductCategory.builder().description("Noodles").build());
        ProductCategory coffee = productCategoryService.create(ProductCategory.builder().description("Coffee").build());
        ProductCategory seafood = productCategoryService.create(ProductCategory.builder().description("Seafood").build());
        ProductCategory appetizers = productCategoryService.create(ProductCategory.builder().description("Appetizers").build());
        ProductCategory alcohol = productCategoryService.create(ProductCategory.builder().description("Alcohol").build());
        ProductCategory iceCream = productCategoryService.create(ProductCategory.builder().description("Ice cream").build());
        ProductCategory souvlaki = productCategoryService.create(ProductCategory.builder().description("Souvlaki").build());
        ProductCategory anapsiktika = productCategoryService.create(ProductCategory.builder().description("Anapsiktika").build());
        ProductCategory sandwich = productCategoryService.create(ProductCategory.builder().description("Sandwich").build());
        ProductCategory skepastes = productCategoryService.create(ProductCategory.builder().description("Skepastes").build());
        ProductCategory pizza = productCategoryService.create(ProductCategory.builder().description("Pizza").build());
        ProductCategory sushi = productCategoryService.create(ProductCategory.builder().description("Sushi").build());
        ProductCategory tea = productCategoryService.create(ProductCategory.builder().description("Tea").build());
        ProductCategory poikilies = productCategoryService.create(ProductCategory.builder().description("Poikilies").build());
        ProductCategory burger = productCategoryService.create(ProductCategory.builder().description("Burger").build());
        ProductCategory merides = productCategoryService.create(ProductCategory.builder().description("Merides").build());
        ProductCategory sauce = productCategoryService.create(ProductCategory.builder().description("Sauce").build());

        StoreCategory tavern = storeCategoryService.create(StoreCategory.builder().description("Tavern").build());
        StoreCategory coffeeShop = storeCategoryService.create(StoreCategory.builder().description("Coffee Shop").build());
        StoreCategory iceCreamShop = storeCategoryService.create(StoreCategory.builder().description("Ice Cream Shop").build());
        StoreCategory souvlatzidiko = storeCategoryService.create(StoreCategory.builder().description("Souvlatzidiko").build());
        StoreCategory asian = storeCategoryService.create(StoreCategory.builder().description("Asian restaurant").build());
        StoreCategory mezedopoleio = storeCategoryService.create(StoreCategory.builder().description("Mezedopoleio").build());

        storeCategoryService.findAll().forEach(sc -> logger.info("Store category {} created.", sc));

        Store louloudaki = storeService.initiateStore("Louloudaki",mezedopoleio,"Plateia Eleftheris 51 71303 Athens Attica");
        louloudaki.setImage("https://scontent.fath4-2.fna.fbcdn.net/v/t39.30808-6/304138197_490310123099500_3228594208283622768_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=e3f864&_nc_ohc=Wj9MigUQ7xUAX8ssch0&_nc_oc=AQmQoUhCmVKCDMxj3ZuPQ5pPkGhjGnqjlQdUsc_eO2OvuxrZ2BYb1yTe6LPuzujBfE0&_nc_ht=scontent.fath4-2.fna&oh=00_AT_IyYwBo2kdevjh7lZ_M4SNoh8UT33T628kHfuVmT-1uA&oe=632A12C2");
        storeService.addStoreProduct(louloudaki,salad,"Louloudaki Salad","With vinegar sauce and manouri cheese", BigDecimal.valueOf(4.5),"");
        storeService.addStoreProduct(louloudaki,appetizers,"Fried Liver","empty", BigDecimal.valueOf(5),"https://assets-cdn.kathmandupost.com/uploads/source/news/2019/lifestyle/shutterstock_1272921334.jpg");
        storeService.addStoreProduct(louloudaki,appetizers,"Fried Zucchini","empty", BigDecimal.valueOf(3),"");
        storeService.addStoreProduct(louloudaki,alcohol,"Red wine","250 ml", BigDecimal.valueOf(2),"");
        storeService.create(louloudaki);

        Store mushisi = storeService.initiateStore("Mushisi",asian,"Plateia Makedonon 51 71203 Athens Attica");
        mushisi.setImage("http://d20aeo683mqd6t.cloudfront.net/articles/title_images/000/027/021/medium/pixta_18413006_S.jpg?2017");
        storeService.addStoreProduct(mushisi,noodles,"Rice Noodles","Panarismena me zoumero stithos kotopoulo kai moustarda", BigDecimal.valueOf(6.25),"");
        storeService.addStoreProduct(mushisi,appetizers,"Onion Rings","Traganes rodeles kremmidiou", BigDecimal.valueOf(5),"");
        storeService.addStoreProduct(mushisi,sauce,"Ketchup","50 ml", BigDecimal.valueOf(1),"");
        storeService.addStoreProduct(mushisi,seafood,"Shrimps","with coconut sauce", BigDecimal.valueOf(2),"");
        storeService.create(mushisi);

        Store natsume = storeService.initiateStore("Natsume",asian,"Plateia Eleftheris 2 71303 Athens Attica");
        natsume.setImage("https://uploads-ssl.webflow.com/5c30841dedb5fd496eb1396c/6187a221326ced5eb2c9cfaa_tF2XDzMDsn0z7ohkrivsEAXIOEthWAUI8-QTJzmdMX9f-gIMLh9Utkflej5xn9B5w0YWy_O4rIIJ9T7s5o4hgaPNlaN7uEmMDMkWWIEDutos4gGA4wkdCy7lneP-6axP_cnQrYKi.png");
        storeService.addStoreProduct(natsume,appetizers,"Vegan nugets","Panarismena laxtarista nuggets sinidevmena me patates tiganites", BigDecimal.valueOf(7.1),"");
        storeService.addStoreProduct(natsume,appetizers,"Apaki spring rolls","empty", BigDecimal.valueOf(5),"");
        storeService.addStoreProduct(natsume,sushi,"Sushi with avocado","empty", BigDecimal.valueOf(3),"");
        storeService.create(natsume);

        Store zorbas = storeService.initiateStore("Zorbas",souvlatzidiko,"Kornarou 25 71103 Athens Attica");
        zorbas.setImage("https://zorbas-seaside-restaurant.com/wp-content/uploads/2022/01/chicken-souvlaki.png");
        storeService.addStoreProduct(zorbas,souvlaki,"Souvlaki kotopoulo","Me laxano patates ntomata kremmudi", BigDecimal.valueOf(3.5),"");
        storeService.create(zorbas);

        Store coffeeIsland = storeService.initiateStore("Coffee Island",coffeeShop,"Makariou 5 74303 Athens Attica");
        coffeeIsland.setImage("https://th.bing.com/th/id/R.c805f8ce5b777b281de7a904e0f7d44b?rik=K11nPjbql2ACLA&pid=ImgRaw&r=0");
        storeService.addStoreProduct(coffeeIsland,coffee,"Greek coffee","emoty", BigDecimal.valueOf(2),"");
        storeService.create(coffeeIsland);

        Store xelona = storeService.initiateStore("Xelona",mezedopoleio,"Kornarou 2 70303 Athens Attica");
        xelona.setImage("https://scontent.fath4-2.fna.fbcdn.net/v/t39.30808-6/296156254_2008631672657602_1411545847266347075_n.png?_nc_cat=101&ccb=1-7&_nc_sid=e3f864&_nc_ohc=DjHJNnjtn4QAX_EObp2&_nc_ht=scontent.fath4-2.fna&oh=00_AT8x3T_vXCDvF3XMsSdhKC-RRBqphnHqHmfKXkFiD8xHKw&oe=63290E54");
        storeService.addStoreProduct(xelona,appetizers,"Fried Zucchini","empty", BigDecimal.valueOf(3.5),"");
        storeService.create(xelona);

        Store kagiampis = storeService.initiateStore("O Kafenes tou Kagiampi",mezedopoleio,"Katexaki 50 74303 Athens Attica");
        kagiampis.setImage("https://www.gastronomos.gr/wp-content/uploads/2022/09/Sisli-14-Gastronomos-1536x864.jpg");
        storeService.addStoreProduct(kagiampis,appetizers,"Tzatziki","empty", BigDecimal.valueOf(3.1),"");
        storeService.create(kagiampis);

        Store pratos = storeService.initiateStore("Pratos",iceCreamShop,"Katexaki 23 74303 Athens Attica");
        pratos.setImage("https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/2C2D5856-92AE-4D1F-BACB-50751CB621DF/Derivates/B32DA530-AC76-49A9-A414-531518309591.jpg");
        storeService.addStoreProduct(pratos,iceCream,"Vanillia ice cream","empty", BigDecimal.valueOf(3.1),"");
        storeService.create(pratos);

        PaymentCard.builder().build();

        Account firstAccount = accountService.findByEmail("mommykmr@hotmail.red");
        Order firstOrder = orderService.initiateOrder(firstAccount);
        orderService.addItem(firstOrder, storeService.getStoreProduct(louloudaki, 2L), 2);
        orderService.addItem(firstOrder, storeService.getStoreProduct(louloudaki, 1L), 2);
        orderService.addItem(firstOrder, storeService.getStoreProduct(louloudaki, 3L), 2);
        orderService.proceedToCheckout(firstOrder,PaymentMethod.CASH);
        orderService.checkout(firstOrder);

        Account secondCustomer = accountService.findByPhoneNumber("6971078143");
        Order secondOrder = orderService.initiateOrder(secondCustomer);
        orderService.addItem(secondOrder, storeService.getStoreProduct(natsume, 9L), 2);
        orderService.addItem(secondOrder, storeService.getStoreProduct(natsume, 10L), 2);
        orderService.proceedToCheckout(secondOrder,PaymentMethod.CASH);
        orderService.checkout(secondOrder);

        Account thirdAccount = accountService.findByPhoneNumber("6948361237");
        Order thirdOrder = orderService.initiateOrder(thirdAccount);
        orderService.addItem(thirdOrder, storeService.getStoreProduct(natsume, 11L), 2);
        orderService.addItem(thirdOrder, storeService.getStoreProduct(natsume, 9L), 2);
        orderService.addItem(thirdOrder, storeService.getStoreProduct(natsume, 10L), 2);
        orderService.proceedToCheckout(thirdOrder,PaymentMethod.CASH);
        orderService.checkout(thirdOrder);

        Order fourthOrder = orderService.initiateOrder(firstAccount);
        orderService.addItem(fourthOrder, storeService.getStoreProduct(louloudaki, 2L), 2);
        orderService.addItem(fourthOrder, storeService.getStoreProduct(louloudaki, 1L), 2);
        orderService.proceedToCheckout(fourthOrder,PaymentMethod.CASH);
        orderService.checkout(fourthOrder);

        Order fifthOrder = orderService.initiateOrder(firstAccount);
        orderService.addItem(fifthOrder, storeService.getStoreProduct(louloudaki, 2L), 2);
        orderService.addItem(fifthOrder, storeService.getStoreProduct(louloudaki, 4L), 2);
        orderService.proceedToCheckout(fifthOrder,PaymentMethod.CASH);
        orderService.checkout(fifthOrder);

        Order sixthOrder = orderService.initiateOrder(secondCustomer);
        orderService.addItem(sixthOrder, storeService.getStoreProduct(mushisi, 5L), 2);
        orderService.proceedToCheckout(sixthOrder,PaymentMethod.CASH);
        orderService.checkout(sixthOrder);

        Order seventhOrder = orderService.initiateOrder(thirdAccount);
        orderService.addItem(seventhOrder, storeService.getStoreProduct(kagiampis, 15L), 2);
        orderService.proceedToCheckout(seventhOrder,PaymentMethod.CASH);
        orderService.checkout(seventhOrder);

        Order eightOrder = orderService.initiateOrder(firstAccount);
        orderService.addItem(eightOrder, storeService.getStoreProduct(pratos, 16L), 2);
        orderService.proceedToCheckout(eightOrder,PaymentMethod.CASH);
        orderService.checkout(eightOrder);

        Order nineOrder = orderService.initiateOrder(firstAccount);
        orderService.addItem(nineOrder, storeService.getStoreProduct(coffeeIsland, 13L), 2);
        orderService.proceedToCheckout(nineOrder,PaymentMethod.CASH);
        orderService.checkout(nineOrder);

        Order tenOrder = orderService.initiateOrder(firstAccount);
        orderService.addItem(tenOrder, storeService.getStoreProduct(kagiampis, 15L), 2);
        orderService.proceedToCheckout(tenOrder,PaymentMethod.CASH);
        orderService.checkout(tenOrder);

        Order elevenOrder = orderService.initiateOrder(firstAccount);
        orderService.addItem(elevenOrder, storeService.getStoreProduct(coffeeIsland, 13L), 2);
        orderService.proceedToCheckout(elevenOrder,PaymentMethod.CASH);
        orderService.checkout(elevenOrder);

        AtomicInteger counter = new AtomicInteger(1);
        logger.info("-----Top 10 store products-----");
        storeService.reportTop10StoreProducts().forEach(tp -> logger.info("Store product with id {}, appears with frequency {}.", tp.getKey(),tp.getValue()));
        logger.info("-----Most famous stores based on orders-----");
        storeService.reportTopStores().forEach(tp -> logger.info("Store with name {}, is {} on the list.", tp.getFirstValue(), counter.getAndIncrement()));
        logger.info("-----Most famous stores per category based on orders-----");
        storeService.reportTopStoresPerCategory(5L).forEach( tp->logger.info("The store with name {}, appears {} times in different orders.",storeService.get(Long.parseLong(tp.getKey())).getName(),tp.getValue()));
      }
}
