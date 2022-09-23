package com.acme.team7.ACMEDeliveryService.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_ITEMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDER_ITEMS_SEQ", initialValue = 1, allocationSize = 1)
public class OrderItem extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JsonIgnore
    private Order order;

    @ManyToOne
    @NotNull
    private StoreProduct storeProduct;

    @Min(value = 1, message = "At least one product is required.")
    @Column(nullable = false)
    @NotNull(message = "Quantity of the product is required.")
    private Integer quantity;

    @Column(precision = 6, scale = 2, nullable = false)
    @NotNull(message = "Price is required.")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal orderPrice;
}
