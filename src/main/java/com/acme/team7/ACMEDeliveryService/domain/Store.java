package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STORES")
@SequenceGenerator(name = "idGenerator", sequenceName = "STORES_SEQ", initialValue = 1, allocationSize = 1)
public class Store extends BaseModel {

    @ToString.Exclude
    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Product> products;

    @Column(length = 30, nullable = false)
    @NotNull
    private String name;

    @Column(length = 50, nullable = false)
    @NotNull
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull
    private StoreCategory storeCategory;
}
