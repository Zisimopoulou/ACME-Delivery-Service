package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private StoreProduct storeProduct;

    @Min(value = 1, message = "At least one product is required.")
    @Column(nullable = false)
    @Pattern(regexp = "[0-9]+",message = "Quantity must be an integer.")
    @NotNull
    private Integer quantity;

    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull
    private BigDecimal price;
}
