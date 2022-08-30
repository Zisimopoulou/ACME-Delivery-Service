package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;

@Component
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
public class CreditCard {
    @CreditCardNumber
    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})")
    private String cardNumber;

    @NotNull
    @Pattern(regexp="(^$|[0-9]{3})")
    private String cvcCode;

    @NotNull
    @Past
    private SimpleDateFormat expirationDate;

    @Column(length = 30, nullable = false)
    @NotNull(message = "Full name is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must be alphabetic.")
    private String name;
}
