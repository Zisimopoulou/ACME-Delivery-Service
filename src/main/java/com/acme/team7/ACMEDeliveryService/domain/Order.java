package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Table(name = "ORDERS", indexes = {@Index(name = "ORDERS_IDX_01", columnList = "serial")})
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseModel{

    @NotNull(message = "Serial is required.")
    @Column(length = 30, nullable = false, unique = true)
    @NotEmpty
    private String serial;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @NotNull(message = "Account is required.")
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
//    @FutureOrPresent
    @NotNull(message = "Submission date is required.")
    private Date submissionDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NotNull(message = "Order items are required.")
    private Set<@NotNull OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;

    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull(message = "Total cost is required.")
//    @Pattern(regexp = "^0\\.(?!0+$)\\d{1,2}$", message = "Total cost must be numeric with at most two decimal places.")
    private BigDecimal totalCost;
}
