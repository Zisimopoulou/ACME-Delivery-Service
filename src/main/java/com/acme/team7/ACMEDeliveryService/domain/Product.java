package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCTS_SEQ", initialValue = 1, allocationSize = 1)

public class Product extends BaseModel {

    @Column(length = 50, nullable = false)
    private String name;
    @Column(precision = 10, scale = 2,nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ProductCategory productcategory;
    @ManyToOne(fetch = FetchType,EAGER,optional=false)
    private Store store;


}
