package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STORE_CATEGORIES")
@SequenceGenerator(name = "idGenerator", sequenceName = "STORE_CATEGORIES_SEQ", initialValue = 1, allocationSize = 1)
public class StoreCategory extends BaseModel{

    @Column(length = 50, nullable = false)
    @NotNull(message = "Store category description is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Store categories must be alphabetic.")
    private String description;
}
