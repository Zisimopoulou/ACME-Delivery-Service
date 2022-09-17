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
@Table(name = "PRODUCT_CATEGORIESS")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCT_CATEGORIESS_SEQ", initialValue = 1, allocationSize = 1)
public class ProductCategory extends BaseModel {

    @NotNull(message = "Product category is required.")
    @Column(length = 50, nullable = false)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Product name must be alphabetic.")
    private String description;

}
