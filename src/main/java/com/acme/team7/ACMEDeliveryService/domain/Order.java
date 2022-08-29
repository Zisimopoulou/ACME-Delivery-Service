package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseModel{

    @Column(length = 30, nullable = false, unique = true)
    @NotEmpty
    private String serial;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    private Account account;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @FutureOrPresent
    @NotNull
    private Date submissionDate;
    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NotNull
    private Set<@NotNull OrderItem> orderItems;
    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    @NotNull
    private PaymentMethod paymentMethod;
    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull
    private BigDecimal totalCost;

}
