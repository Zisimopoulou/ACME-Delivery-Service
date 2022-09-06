package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STOREPRODUCT")
@SequenceGenerator(name = "idGenerator", sequenceName = "STOREPRODUCT_SEQ", initialValue = 1, allocationSize = 1)
public class StoreProduct extends BaseModel {

    @Column(length = 50, nullable = false)
    @NotEmpty(message = "Store product name is required.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Store product name must be alphabetic.")
    private String name;

    @EqualsAndHashCode.Exclude
    @Column(length = 100)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Store product details must be alphanumeric.") //correct pattern or empty
    private String details;

    @EqualsAndHashCode.Exclude
    @Column(precision = 10, scale = 2,nullable = false)
    @NotNull(message = "Price is required.")
//    @Pattern(regexp = "^0\\.(?!0+$)\\d{1,2}$", message = "Price must be numeric with at most two decimal places.")
    private BigDecimal price;

    @EqualsAndHashCode.Exclude
    @Column
    private String image;

    @ToString.Exclude
    @ManyToOne
    @JsonIgnore
    private Store store;

    @NotNull(message = "Product is required.")
    @ManyToOne
    private Product product;
}