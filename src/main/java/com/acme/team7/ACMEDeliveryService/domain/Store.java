package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @Column(length = 50, nullable = false)
    @NotNull(message = "Store name is required.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Store name must be alphabetic.")
    private String name;

    @Column(length = 50, nullable = false)
    @NotNull(message = "Store address is required.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Address must be alphanumeric.")
    private String address;

    @Column
    private String image;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull(message = "Store category is required.")
    private StoreCategory storeCategory;

    @ToString.Exclude
    @NotNull(message = "Store products are required.")
    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<@NotNull StoreProduct> storeProducts;
}
