package com.acme.team7.ACMEDeliveryService.domain;

import com.acme.team7.ACMEDeliveryService.transfer.KeyTwoValues;
import com.acme.team7.ACMEDeliveryService.transfer.KeyValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@NamedNativeQuery(name = "Store.GetLazyStores",
        query ="""
			SELECT stores.id as storeId, stores.name as store_name, stores.image as store_image
            FROM STORES
			""",
        resultSetMapping = "GetLazyStores")
@SqlResultSetMapping(name = "GetLazyStores",
        classes = @ConstructorResult(
                targetClass = KeyTwoValues.class,
                columns = {
                        @ColumnResult(name = "storeId", type = String.class),
                        @ColumnResult(name = "store_name", type = String.class),
                        @ColumnResult(name = "store_image", type = String.class)
                }
        )
)

@NamedNativeQuery(name = "Store.ReportTop10StoreProducts",
        query ="""
			SELECT STOREPRODUCT_ID as storeProductId, COUNT(STOREPRODUCT_ID) as frequency
            FROM ORDER_ITEMS
            INNER JOIN STORE_PRODUCTS ON STORE_PRODUCTS.ID=ORDER_ITEMS.STOREPRODUCT_ID
            GROUP BY STOREPRODUCT_ID
            ORDER BY COUNT(STOREPRODUCT_ID) DESC
            FETCH NEXT 10 ROWS ONLY
			""",
        resultSetMapping = "ReportTop10StoreProducts")
@SqlResultSetMapping(name = "ReportTop10StoreProducts",
        classes = @ConstructorResult(
                targetClass = KeyValue.class,
                columns = {
                        @ColumnResult(name = "storeProductId", type = String.class),
                        @ColumnResult(name = "frequency", type = Long.class)
                }
        )
)

@NamedNativeQuery(name = "Store.ReportTopStores",
        query ="""
			SELECT stores.id as storeId, stores.name as storeName, stores.image as storeImage, COUNT(store_id)
            FROM STORES
            INNER JOIN  (
               SELECT order_id, store_id
               FROM ORDER_ITEMS
               INNER JOIN STORE_PRODUCTS ON STORE_PRODUCTS.id=order_items.STOREPRODUCT_id
               Group by order_id, store_id
                      ) ON store_id=stores.id
               GROUP BY stores.id,stores.name,stores.image
               ORDER BY COUNT(store_id) DESC
               FETCH NEXT 20 ROWS ONLY
			""",
        resultSetMapping = "ReportTopStores")
@SqlResultSetMapping(name = "ReportTopStores",
        classes = @ConstructorResult(
                targetClass = KeyTwoValues.class,
                columns = {
                        @ColumnResult(name = "storeId", type = String.class),
                        @ColumnResult(name = "storeName", type = String.class),
                        @ColumnResult(name = "storeImage", type = String.class)
                }
        )
)

@NamedNativeQuery(name = "Store.ReportTopStoresPerCategory",
        query ="""
			SELECT store_id as storeId, COUNT(store_id) as frequency
            FROM (
                       SELECT order_id, store_id
                       FROM ORDER_ITEMS
                       INNER JOIN (
                                    SELECT store_id, STORE_PRODUCTS.id as spid
                                    FROM STORE_PRODUCTS
                                    INNER JOIN STORES ON stores.id=STORE_PRODUCTS.store_id
                                    WHERE storecategory_id=?
                                   )
                       ON spid=order_items.storeproduct_id 
                       Group by order_id, store_id 
                   )
                       GROUP BY store_id
                       ORDER BY frequency DESC
                       FETCH NEXT 10 ROWS ONLY
			""",
        resultSetMapping = "ReportTopStoresPerCategory")
@SqlResultSetMapping(name = "ReportTopStoresPerCategory",
        classes = @ConstructorResult(
                targetClass = KeyValue.class,
                columns = {
                        @ColumnResult(name = "storeId", type = String.class),
                        @ColumnResult(name = "frequency", type = Long.class)
                }
        )
)

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STORES")
@SequenceGenerator(name = "idGenerator", sequenceName = "STORES_SEQ", initialValue = 1, allocationSize = 1)
public class Store extends BaseModel {

    @Column(length = 50, nullable = false)
    @NotNull(message = "Store name is required.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Store name must be alphabetic.")
    private String name;

    @Column(length = 50, nullable = false)
    @NotNull(message = "Store address is required.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Address must be alphanumeric.")
    private String address;

    @Column(length = 500)
    private String image;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull(message = "Store category is required.")
    private StoreCategory storeCategory;

    @ToString.Exclude
    @NotNull(message = "Store products are required.")
    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<@NotNull StoreProduct> storeProducts;
}
