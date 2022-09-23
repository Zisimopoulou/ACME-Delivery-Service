package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Account is required.")
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull(message = "Submission date is required.")
    private Date submissionDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @NotNull(message = "Order items are required.")
    private Set<@NotNull OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;

    @Column(precision = 8, scale = 2, nullable = false)
    @NotNull(message = "Total cost is required.")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal totalCost;
}
