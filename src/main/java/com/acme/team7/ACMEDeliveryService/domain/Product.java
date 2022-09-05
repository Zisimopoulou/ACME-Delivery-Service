package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCTS_SEQ", initialValue = 1, allocationSize = 1)
public class Product extends BaseModel {

    @Column(length = 50, nullable = false)
    @NotEmpty(message = "Name of the product is required.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Product name must be alphabetic.")
    private String name;

    @NotNull(message = "Product category is required.")
    private ProductCategory productcategory;

}
