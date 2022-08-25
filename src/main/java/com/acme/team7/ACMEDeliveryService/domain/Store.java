package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STORES")
@SequenceGenerator(name = "idGenerator", sequenceName = "STORES", initialValue = 1, allocationSize = 1)
public class Store extends BaseModel {

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Product> products;

    private String name;

    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private StoreCategory storeCategory;

    private String review;
}