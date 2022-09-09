package com.acme.team7.ACMEDeliveryService.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ToString.Exclude
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;
//gt an valo to fetch = FetchType.LAZY sto storeproduct den kanei get ta orders?
    @ManyToOne
    @NotNull
    private StoreProduct storeProduct;

    @Min(value = 1, message = "At least one product is required.")
    @Column(nullable = false)
//    @Pattern(regexp = "[0-9]+",message = "Quantity must be an integer.")
    @NotNull(message = "Quantity of the product is required.")
    private Integer quantity;

    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull(message = "Price is required.")
//    @Pattern(regexp = "^0\\.(?!0+$)\\d{1,2}$", message = "Price must be numeric with at most two decimal places.")
    private BigDecimal price;
}
