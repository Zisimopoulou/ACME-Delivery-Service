package com.acme.team7.ACMEDeliveryService.domain;

import com.acme.team7.ACMEDeliveryService.transfer.KeySixValues;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@NamedNativeQuery(name = "Order.GetAccountOrders",
        query ="""
		   SELECT submissionDate as sub_date, orders.account_id as account_id, paymentMethod as payment_method, totalCost as total_cost, quantity as quantity, orderPrice as price, name as product_name
           FROM orders
           INNER JOIN (
           SELECT order_items.order_id, store_products.name, order_items.orderPrice, order_items.quantity
           FROM order_items
           INNER JOIN store_products ON store_products.id=order_items.storeproduct_id
           )
           ON order_id=orders.id
           WHERE orders.account_id=?
			""",
        resultSetMapping = "GetAccountOrders")
@SqlResultSetMapping(name = "GetAccountOrders",
        classes = @ConstructorResult(
                targetClass = KeySixValues.class,
                columns = {
                        @ColumnResult(name = "account_id", type = String.class),
                        @ColumnResult(name = "sub_date", type = Date.class),
                        @ColumnResult(name = "payment_method", type = String.class),
                        @ColumnResult(name = "total_cost", type = BigDecimal.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "product_name", type = String.class)

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
@Table(name = "ORDERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Account is required.")
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull(message = "Submission date is required.")
    private Date submissionDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @NotNull(message = "Order items are required.")
    private Set<@NotNull OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;

    @Column(precision = 8, scale = 2, nullable = false)
    @NotNull(message = "Total cost is required.")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal totalCost;
}
