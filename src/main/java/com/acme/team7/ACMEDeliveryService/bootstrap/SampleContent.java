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

        StoreCategory tavern = storeCategoryService.create(StoreCategory.builder().description("Tavern").image("https://blog.e-table.gr/wp-content/uploads/2016/10/11013427_760656340678160_6550820498649138632_n.png").build());
        StoreCategory coffeeShop = storeCategoryService.create(StoreCategory.builder().description("CoffeeShop").image("https://mir-s3-cdn-cf.behance.net/project_modules/2800_opt_1/f5843852864027.591f18d2ba0f8.jpg").build());
        StoreCategory iceCreamShop = storeCategoryService.create(StoreCategory.builder().description("IceCream").image("https://www.ft.com/__origami/service/image/v2/images/raw/https://d1e00ek4ebabms.cloudfront.net/production/6a665280-eebe-49e9-b16c-d823131c87de.jpg?source=next&amp;fit=scale-down&amp;quality=highest&amp;width=1067").build());
        StoreCategory souvlatzidiko = storeCategoryService.create(StoreCategory.builder().description("Souvlatzidiko").image("https://eyefindyou.gr/wp-content/uploads/2022/08/IMG_28Sep2020114740AM.jpg").build());
        StoreCategory asian = storeCategoryService.create(StoreCategory.builder().description("Asian").image("https://www.visitstockholm.com/media/original_images/wang1.jpg").build());
        StoreCategory mezedopoleio = storeCategoryService.create(StoreCategory.builder().description("Mezedopoleio").image("https://www.athensmagazine.gr/photos/w_800px/articles/202007/_paraskeyh_mezedopwleia.jpg").build());

        storeCategoryService.findAll().forEach(sc -> logger.info("Store category {} created.", sc));

        Store louloudaki = storeService.initiateStore("Louloudaki",mezedopoleio,"Plateia Eleftheris 51 71303 Athens Attica");
        louloudaki.setImage("https://scontent.fath4-2.fna.fbcdn.net/v/t39.30808-6/304138197_490310123099500_3228594208283622768_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=e3f864&_nc_ohc=Wj9MigUQ7xUAX8ssch0&_nc_oc=AQmQoUhCmVKCDMxj3ZuPQ5pPkGhjGnqjlQdUsc_eO2OvuxrZ2BYb1yTe6LPuzujBfE0&_nc_ht=scontent.fath4-2.fna&oh=00_AT_IyYwBo2kdevjh7lZ_M4SNoh8UT33T628kHfuVmT-1uA&oe=632A12C2");
        storeService.addStoreProduct(louloudaki,salad,"Louloudaki Salad","With vinegar sauce and manouri cheese", BigDecimal.valueOf(4.5),"https://tmbidigitalassetsazure.blob.core.windows.net/rms3-prod/attachments/37/1200x1200/exps40656_TH1789929D22A_RMS.jpg");
        storeService.addStoreProduct(louloudaki,appetizers,"Fried Liver","", BigDecimal.valueOf(5),"https://assets-cdn.kathmandupost.com/uploads/source/news/2019/lifestyle/shutterstock_1272921334.jpg");
        storeService.addStoreProduct(louloudaki,appetizers,"Fried Zucchini","", BigDecimal.valueOf(3),"https://noshingwiththenolands.com/wp-content/uploads/2021/03/Fried-Zucchini-1200-x-1200.jpg");
        storeService.addStoreProduct(louloudaki,alcohol,"Red wine","250 ml", BigDecimal.valueOf(2),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6lcZoYroKsD-ZLvZAX73VjYHumwW_JKR6pg&usqp=CAU");
        storeService.create(louloudaki);

        Store mushisi = storeService.initiateStore("Mushisi",asian,"Plateia Makedonon 51 71203 Athens Attica");
        mushisi.setImage("http://d20aeo683mqd6t.cloudfront.net/articles/title_images/000/027/021/medium/pixta_18413006_S.jpg?2017");
        storeService.addStoreProduct(mushisi,noodles,"Rice Noodles","With chicken and tomato", BigDecimal.valueOf(6.25),"https://www.seriouseats.com/thmb/KHPRZ5PXARmmc2tejrE-_YWPU4w=/1125x1125/smart/filters:no_upscale()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__recipes__images__2015__05__20150424-singapore-noodles-shao-zhong-20-130b0ed9d8ad45b3bd164cbe1328abef.jpg");
        storeService.addStoreProduct(mushisi,appetizers,"Onion Rings","Crispy", BigDecimal.valueOf(5),"https://i0.wp.com/cooknshare.com/wp-content/uploads/2021/05/onionringspic.jpg?fit=1493%2C840&ssl=1");
        storeService.addStoreProduct(mushisi,sauce,"Ketchup","50 ml", BigDecimal.valueOf(1),"https://www.thepetitecook.com/wp-content/uploads/2019/06/homemade-ketchup.jpg");
        storeService.addStoreProduct(mushisi,seafood,"Shrimps","with coconut sauce", BigDecimal.valueOf(2),"https://www.foodandwine.com/thmb/QOMDlwiYcWiwWpFOQM2ahMwDFv4=/2000x1333/filters:fill(auto,1)/honey-pepper-coconut-shrimp-FT-RECIPE0620-2ced1c1798824999bd64b3171f7c63b6.jpg");
        storeService.create(mushisi);

        Store natsume = storeService.initiateStore("Natsume",asian,"Plateia Eleftheris 2 71303 Athens Attica");
        natsume.setImage("https://excite.mochimune.jp/images/food_natsume_3.jpg");
        storeService.addStoreProduct(natsume,appetizers,"Vegan nugets","With white sauce", BigDecimal.valueOf(7.1),"https://www.giorgostsoulis.com/media/k2/items/cache/4cd5973a7c2085986240cae9b1f23d5c_L.jpg");
        storeService.addStoreProduct(natsume,appetizers,"Apaki spring rolls","", BigDecimal.valueOf(5),"https://media-cdn.tripadvisor.com/media/photo-s/1b/b8/6d/43/phyllo-rolls.jpg");
        storeService.addStoreProduct(natsume,sushi,"Sushi with avocado","", BigDecimal.valueOf(3),"https://brokebankvegan.com/wp-content/uploads/2022/06/Avocado-Roll-39.jpg");
        storeService.create(natsume);

        Store zorbas = storeService.initiateStore("Zorbas",souvlatzidiko,"Kornarou 25 71103 Athens Attica");
        zorbas.setImage("https://zorbas-seaside-restaurant.com/wp-content/uploads/2022/01/chicken-souvlaki.png");
        storeService.addStoreProduct(zorbas,souvlaki,"Souvlaki kotopoulo","", BigDecimal.valueOf(3.5),"https://elviart.com/wp-content/uploads/2017/05/souvlaki-kotopoulo-800x600.jpg");
        storeService.create(zorbas);

        Store coffeeIsland = storeService.initiateStore("Coffee Island",coffeeShop,"Makariou 5 74303 Athens Attica");
        coffeeIsland.setImage("https://th.bing.com/th/id/R.c805f8ce5b777b281de7a904e0f7d44b?rik=K11nPjbql2ACLA&pid=ImgRaw&r=0");
        storeService.addStoreProduct(coffeeIsland,coffee,"Greek coffee","", BigDecimal.valueOf(2),"https://www.thehungrybites.com/wp-content/uploads/2020/05/turkish-greek-coffee-profile.jpg");
        storeService.create(coffeeIsland);

        Store xelona = storeService.initiateStore("The Green Turtle",mezedopoleio,"Kornarou 2 70303 Athens Attica");
        xelona.setImage("https://wtop.com/wp-content/uploads/2015/03/The_Greene_Turtle.jpg");
        storeService.addStoreProduct(xelona,appetizers,"Fried Zucchini","", BigDecimal.valueOf(3.5),"https://www.thespruceeats.com/thmb/7KeyIEZt5OxTEX9jbcSZKN_5GPY=/3600x2402/filters:no_upscale():max_bytes(150000):strip_icc()/oven-fried-zucchini-recipe-2098669-step-14-5c29059146e0fb0001a880f6.jpg");
        storeService.create(xelona);

        Store kagiampis = storeService.initiateStore("Kagiampis",mezedopoleio,"Katexaki 50 74303 Athens Attica");
        kagiampis.setImage("https://www.gastronomos.gr/wp-content/uploads/2022/09/Sisli-14-Gastronomos-1536x864.jpg");
        storeService.addStoreProduct(kagiampis,appetizers,"Tzatziki","", BigDecimal.valueOf(3.1),"https://www.argiro.gr/wp-content/uploads/2021/07/tzatziki-400x400.jpg?v=1650369947");
        storeService.create(kagiampis);

        Store pratos = storeService.initiateStore("Pratos",iceCreamShop,"Katexaki 23 74303 Athens Attica");
        pratos.setImage("https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/2C2D5856-92AE-4D1F-BACB-50751CB621DF/Derivates/B32DA530-AC76-49A9-A414-531518309591.jpg");
        storeService.addStoreProduct(pratos,iceCream,"Vanillia ice cream","", BigDecimal.valueOf(3.1),"https://static.toiimg.com/thumb/63971154.cms?width=573&height=430");
        storeService.create(pratos);

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

        AtomicInteger firstCounter = new AtomicInteger(1);
        AtomicInteger secondCounter = new AtomicInteger(1);
        AtomicInteger thirdCounter = new AtomicInteger(1);
        logger.info("-----Top 10 store products-----");
        storeService.reportTop10StoreProducts().forEach(tp -> logger.info("Store product with name {}, is {} on the list.", tp.getKey(),secondCounter.getAndIncrement()));
        logger.info("-----Most famous stores based on orders-----");
        storeService.reportTopStores().forEach(tp -> logger.info("Store with name {}, is {} on the list.", tp.getFirstValue(), firstCounter.getAndIncrement()));
        logger.info("-----Most famous stores per category based on orders-----");
        Long category = 5L;
        storeService.reportTopStoresPerCategory(category).forEach( tp->logger.info("For store category {}, store with name {}, is {} on the list.",category,tp.getFirstValue(),thirdCounter.getAndIncrement()));
    }
}
