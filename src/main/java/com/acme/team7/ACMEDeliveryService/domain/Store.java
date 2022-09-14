package com.acme.team7.ACMEDeliveryService.domain;

import com.acme.team7.ACMEDeliveryService.transfer.KeyValue;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@NamedNativeQuery(name = "Store.ReportTop10StoreProducts",
        query ="""
			SELECT STOREPRODUCT_ID as storeProductId, COUNT(STOREPRODUCT_ID) as frequency
            FROM ORDER_ITEMS
            INNER JOIN STOREPRODUCT ON STOREPRODUCT.ID=ORDER_ITEMS.STOREPRODUCT_ID
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
			SELECT store_id as storeId, COUNT(store_id) as frequency
            FROM (
            SELECT order_id, store_id
            FROM ORDER_ITEMS
            INNER JOIN STOREPRODUCT ON storeproduct.id=order_items.storeproduct_id
            Group by order_id, store_id
                   )
            GROUP BY store_id
            ORDER BY frequency DESC
            FETCH NEXT 20 ROWS ONLY
			""",
        resultSetMapping = "ReportTopStores")
@SqlResultSetMapping(name = "ReportTopStores",
        classes = @ConstructorResult(
                targetClass = KeyValue.class,
                columns = {
                        @ColumnResult(name = "storeId", type = String.class),
                        @ColumnResult(name = "frequency", type = Long.class)
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
                                    SELECT store_id, storeproduct.id as spid
                                    FROM STOREPRODUCT
                                    INNER JOIN STORES ON stores.id=storeproduct.store_id
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

    @Column
    private String image;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull(message = "Store category is required.")
    private StoreCategory storeCategory;

    @ToString.Exclude
    @NotNull(message = "Store products are required.")
    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<@NotNull StoreProduct> storeProducts;
}
