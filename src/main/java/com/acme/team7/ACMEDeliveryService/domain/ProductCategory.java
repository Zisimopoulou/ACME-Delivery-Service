package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_CATEGORY")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCTS_CATEGORY", initialValue = 1, allocationSize = 1)
public class ProductCategory extends BaseModel{
    @Column(length=50,nullable = false)
    private String description;
}
