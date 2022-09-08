package com.acme.team7.ACMEDeliveryService.bootstrap;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.*;
import com.acme.team7.ACMEDeliveryService.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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
        Product vrastaXorta = productService.create(Product.builder().name("Vrasta xorta").productcategory(ProductCategory.SALAD).build());
        Product laxano = productService.create(Product.builder().name("Laxano").productcategory(ProductCategory.SALAD).build());

        Product riceNoodles = productService.create(Product.builder().name("Rice noodles").productcategory(ProductCategory.NOODLES).build());
        Product vegieNoodles = productService.create(Product.builder().name("Vegie noodles").productcategory(ProductCategory.NOODLES).build());
        Product eggNoodles = productService.create(Product.builder().name("Egg noodles").productcategory(ProductCategory.NOODLES).build());

        Product sashimi = productService.create(Product.builder().name("Sashimi").productcategory(ProductCategory.SUSHI).build());
        Product teriyaki = productService.create(Product.builder().name("Teriyaki").productcategory(ProductCategory.SUSHI).build());
        Product maki = productService.create(Product.builder().name("Maki").productcategory(ProductCategory.SUSHI).build());
        Product nigiri = productService.create(Product.builder().name("Nigiri").productcategory(ProductCategory.SUSHI).build());

        Product skepastiKotopoulo = productService.create(Product.builder().name("Skepasti kotopoulo").productcategory(ProductCategory.SKEPASTES).build());
        Product skepastiXoirino = productService.create(Product.builder().name("Skepasti xoirino").productcategory(ProductCategory.SKEPASTES).build());
        Product skepastiMosxari = productService.create(Product.builder().name("Skepasti mosxari").productcategory(ProductCategory.SKEPASTES).build());
        Product skepastiPanseta = productService.create(Product.builder().name("Skepasti panseta").productcategory(ProductCategory.SKEPASTES).build());

        Product burgerKotopoulo = productService.create(Product.builder().name("Burger kotopoulo").productcategory(ProductCategory.BURGER).build());
        Product burgerMosxari = productService.create(Product.builder().name("Burger mosxari").productcategory(ProductCategory.BURGER).build());
        Product burgerXoirino = productService.create(Product.builder().name("Burger xoirino").productcategory(ProductCategory.BURGER).build());
        Product burgerPanseta = productService.create(Product.builder().name("Burger Panseta").productcategory(ProductCategory.BURGER).build());

        Product poikiliaKreatikonMikri = productService.create(Product.builder().name("Poikilia kreatikon mikri").productcategory(ProductCategory.POIKILIES).build());
        Product poikiliaKreatikonMegali = productService.create(Product.builder().name("Poikilia kreatikon megali").productcategory(ProductCategory.POIKILIES).build());
        Product poikiliaThalassinonMikri = productService.create(Product.builder().name("Poikilia thalassinon mikri").productcategory(ProductCategory.POIKILIES).build());
        Product poikiliaThalassinonMegali = productService.create(Product.builder().name("Poikilia thalassinon megali").productcategory(ProductCategory.POIKILIES).build());

        Product liver = productService.create(Product.builder().name("Liver").productcategory(ProductCategory.APPETIZERS).build());
        Product zucchini = productService.create(Product.builder().name("Zucchini").productcategory(ProductCategory.APPETIZERS).build());
        Product saganaki = productService.create(Product.builder().name("Saganaki").productcategory(ProductCategory.APPETIZERS).build());
        Product mushrooms = productService.create(Product.builder().name("Mushrooms").productcategory(ProductCategory.APPETIZERS).build());
        Product fava = productService.create(Product.builder().name("Fava").productcategory(ProductCategory.APPETIZERS).build());
        Product xoxlioi = productService.create(Product.builder().name("Xoxlioi").productcategory(ProductCategory.APPETIZERS).build());
        Product chickenWings = productService.create(Product.builder().name("Chicken wings").productcategory(ProductCategory.APPETIZERS).build());
        Product onionRings = productService.create(Product.builder().name("Onion rings").productcategory(ProductCategory.APPETIZERS).build());
        Product nugets = productService.create(Product.builder().name("Nugets").productcategory(ProductCategory.APPETIZERS).build());
        Product dumplings = productService.create(Product.builder().name("Dumplings").productcategory(ProductCategory.APPETIZERS).build());
        Product springRolls = productService.create(Product.builder().name("Spring rolls").productcategory(ProductCategory.APPETIZERS).build());

        Product kalamarakia = productService.create(Product.builder().name("Kalamarakia").productcategory(ProductCategory.SEAFOOD).build());
        Product sardeles = productService.create(Product.builder().name("Sardeles").productcategory(ProductCategory.SEAFOOD).build());
        Product garides = productService.create(Product.builder().name("Garides").productcategory(ProductCategory.SEAFOOD).build());
        Product mpakaliaros = productService.create(Product.builder().name("Mpakaliaros").productcategory(ProductCategory.SEAFOOD).build());
        Product midia = productService.create(Product.builder().name("Midia").productcategory(ProductCategory.SEAFOOD).build());

        Product redWine = productService.create(Product.builder().name("Red Wine").productcategory(ProductCategory.ALCOHOL).build());
        Product whiteWine = productService.create(Product.builder().name("White Wine").productcategory(ProductCategory.ALCOHOL).build());
        Product retsina = productService.create(Product.builder().name("Retsina").productcategory(ProductCategory.ALCOHOL).build());
        Product raki = productService.create(Product.builder().name("Raki").productcategory(ProductCategory.ALCOHOL).build());
        Product rakomelo = productService.create(Product.builder().name("Rakomelo").productcategory(ProductCategory.ALCOHOL).build());
        Product tsipouro = productService.create(Product.builder().name("Tsipouro").productcategory(ProductCategory.ALCOHOL).build());
        Product darkBeer = productService.create(Product.builder().name("Dark Beer").productcategory(ProductCategory.ALCOHOL).build());
        Product redBeer = productService.create(Product.builder().name("Red Beer").productcategory(ProductCategory.ALCOHOL).build());
        Product blondeBeer = productService.create(Product.builder().name("Blonde Beer").productcategory(ProductCategory.ALCOHOL).build());

        Product cocaCola = productService.create(Product.builder().name("Coca Cola").productcategory(ProductCategory.ANAPSIKTIKA).build());
        Product sprite = productService.create(Product.builder().name("Sprite").productcategory(ProductCategory.ANAPSIKTIKA).build());
        Product mpiral = productService.create(Product.builder().name("Mpiral").productcategory(ProductCategory.ANAPSIKTIKA).build());

        Product greekCoffee = productService.create(Product.builder().name("Greek Coffee").productcategory(ProductCategory.COFFEE).build());
        Product americano = productService.create(Product.builder().name("Americano").productcategory(ProductCategory.COFFEE).build());
        Product filterCoffee = productService.create(Product.builder().name("Filter Coffee").productcategory(ProductCategory.COFFEE).build());
        Product fredoEspresso = productService.create(Product.builder().name("Fredo espresso").productcategory(ProductCategory.COFFEE).build());
        Product cappuccino = productService.create(Product.builder().name("Cappuccino").productcategory(ProductCategory.COFFEE).build());
        Product espresso = productService.create(Product.builder().name("Espresso").productcategory(ProductCategory.COFFEE).build());
        Product frapes = productService.create(Product.builder().name("Frapes").productcategory(ProductCategory.COFFEE).build());

        Product souvlakiKotopoulo = productService.create(Product.builder().name("Souvlaki Kotopoulo").productcategory(ProductCategory.SOUVLAKI).build());
        Product souvlakiXoirino = productService.create(Product.builder().name("Souvlaki Xoirino").productcategory(ProductCategory.SOUVLAKI).build());
        Product souvlakiMpiftekiKotopoulo = productService.create(Product.builder().name("Souvlaki Mpifteki").productcategory(ProductCategory.SOUVLAKI).build());
        Product souvlakiPanseta = productService.create(Product.builder().name("Souvlaki Panseta").productcategory(ProductCategory.SOUVLAKI).build());

        Product mpiftekiMosxarisioSxaras = productService.create(Product.builder().name("Mpifteki sxaras").productcategory(ProductCategory.SINGLEPIECE).build());
        Product kotopouloSxaras = productService.create(Product.builder().name("Kotopoulo sxaras").productcategory(ProductCategory.SINGLEPIECE).build());
        Product pansetaSxaras = productService.create(Product.builder().name("Panseta sxaras").productcategory(ProductCategory.SINGLEPIECE).build());
        Product mprizolaXoirini = productService.create(Product.builder().name("Mprizola").productcategory(ProductCategory.SINGLEPIECE).build());

        Product vanilliaFlavor = productService.create(Product.builder().name("Vanillia Flavor").productcategory(ProductCategory.ICECREAM).build());
        Product chocolateFlavor = productService.create(Product.builder().name("Chocolate Flavor").productcategory(ProductCategory.ICECREAM).build());
        Product mangoFlavor = productService.create(Product.builder().name("Mango Flavor").productcategory(ProductCategory.ICECREAM).build());
        Product lemonFlavor = productService.create(Product.builder().name("Lemon Flavor").productcategory(ProductCategory.ICECREAM).build());
        Product buenoFlavor = productService.create(Product.builder().name("Bueno Flavor").productcategory(ProductCategory.ICECREAM).build());
        Product coconutFlavor = productService.create(Product.builder().name("Coconut Flavor").productcategory(ProductCategory.ICECREAM).build());

        Product tzatziki = productService.create(Product.builder().name("Tzatziki").productcategory(ProductCategory.SAUCE).build());
        Product ketchup = productService.create(Product.builder().name("Ketchup").productcategory(ProductCategory.SAUCE).build());
        Product mayonnaise = productService.create(Product.builder().name("Mayonnaise").productcategory(ProductCategory.SAUCE).build());
        Product mustard = productService.create(Product.builder().name("Mustard").productcategory(ProductCategory.SAUCE).build());
        Product tirokafteri = productService.create(Product.builder().name("Tirokafteri").productcategory(ProductCategory.SAUCE).build());
        Product skordalia = productService.create(Product.builder().name("Skordalia").productcategory(ProductCategory.SAUCE).build());

        StoreCategory tavern = storeCategoryService.create(StoreCategory.builder().description("Tavern").build());
        StoreCategory coffeeShop = storeCategoryService.create(StoreCategory.builder().description("Coffee Shop").build());
        StoreCategory iceCreamShop = storeCategoryService.create(StoreCategory.builder().description("Ice Cream Shop").build());
        StoreCategory souvlatzidiko = storeCategoryService.create(StoreCategory.builder().description("Souvlatzidiko").build());
        StoreCategory asian = storeCategoryService.create(StoreCategory.builder().description("Asian restaurant").build());
        StoreCategory mezedopoleio = storeCategoryService.create(StoreCategory.builder().description("Mezedopoleio").build());

        storeCategoryService.findAll().forEach(sc -> log.info("Store category {} created.", sc));

        Store louloudaki = storeService.initiateStore("Louloudaki",tavern,"Plateia Eleftheris 51 71303 Athens Attica");
        louloudaki.setImage("path to flower image");
        storeService.addStoreProduct(louloudaki,xoriatiki,"Louloudaki Salad","With vinegar sauce and manouri cheese", BigDecimal.valueOf(4.5),"");
        storeService.addStoreProduct(louloudaki,liver,"Fried Liver","empty", BigDecimal.valueOf(5),"");
        storeService.addStoreProduct(louloudaki,zucchini,"Fried Zucchini","empty", BigDecimal.valueOf(3),"");
        storeService.addStoreProduct(louloudaki,redWine,"Red wine","250 ml", BigDecimal.valueOf(2),"");
        storeService.create(louloudaki);

        Store mushisi = storeService.initiateStore("Mushisi",tavern,"Plateia Makedonon 51 71203 Athens Attica");
        mushisi.setImage("image");
        storeService.addStoreProduct(mushisi,nugets,"Chicken Nugets","Panarismena me zoumero stithos kotopoulo kai moustarda", BigDecimal.valueOf(6.25),"");
        storeService.addStoreProduct(mushisi,onionRings,"Onion Rings","Traganes rodeles kremmidiou", BigDecimal.valueOf(5),"");
        storeService.addStoreProduct(mushisi,ketchup,"Ketchup","50 ml", BigDecimal.valueOf(1),"");
        storeService.addStoreProduct(mushisi,dumplings,"Shrimp dumpling","empty", BigDecimal.valueOf(2),"");
        storeService.create(mushisi);

        Store natsume = storeService.initiateStore("Natsume",tavern,"Plateia Eleftheris 2 71303 Athens Attica");
        natsume.setImage("image");
        storeService.addStoreProduct(natsume,nugets,"Vegan nugets","Panarismena laxtarista nuggets sinidevmena me patates tiganites", BigDecimal.valueOf(7.1),"");
        storeService.addStoreProduct(natsume,springRolls,"Apaki spring rolls","empty", BigDecimal.valueOf(5),"");
        storeService.addStoreProduct(natsume,dumplings,"Chicken dumplings","empty", BigDecimal.valueOf(3),"");
        storeService.create(natsume);

        Store zorbas = storeService.initiateStore("Zorbas",souvlatzidiko,"Kornarou 25 71103 Athens Attica");
        natsume.setImage("image");
        storeService.addStoreProduct(natsume,souvlakiKotopoulo,"Souvlaki kotopoulo","Me laxano patates ntomata kremmudi", BigDecimal.valueOf(3.5),"");
        storeService.create(natsume);

        Store coffeeIsland = storeService.initiateStore("Coffee Island",coffeeShop,"Makariou 5 74303 Athens Attica");
        natsume.setImage("image");
        storeService.addStoreProduct(natsume,greekCoffee,"Greek coffee","emoty", BigDecimal.valueOf(2),"");
        storeService.create(natsume);

        Store xelona = storeService.initiateStore("Xelona",tavern,"Kornarou 2 70303 Athens Attica");
        natsume.setImage("image");
        storeService.addStoreProduct(natsume,zucchini,"Fried Zucchini","empty", BigDecimal.valueOf(3.5),"");
        storeService.create(xelona);

        Store kagiampis = storeService.initiateStore("Kagiampis",mezedopoleio,"Katexaki 50 74303 Athens Attica");
        natsume.setImage("image");
        storeService.addStoreProduct(natsume,tzatziki,"Tzatziki","empty", BigDecimal.valueOf(3.1),"");
        storeService.create(kagiampis);

        Store pratos = storeService.initiateStore("Pratos",iceCreamShop,"Katexaki 23 74303 Athens Attica");
        natsume.setImage("image");
        storeService.addStoreProduct(natsume,vanilliaFlavor,"Vanillia ice cream","empty", BigDecimal.valueOf(3.1),"");
        storeService.create(pratos);

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
        firstOrder.setOrderNumber("0000010001");

        Iterator<StoreProduct> storeProductIterator = louloudaki.getStoreProducts().iterator();
        List<StoreProduct> storeProducts = new ArrayList<>();

        while(storeProductIterator.hasNext()) {
            storeProducts.add(storeProductIterator.next());
        }

        log.info(storeProducts.toString());

        orderService.addItem(firstOrder, storeProducts.get(0), 2);

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
