package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;

@Component
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
public class PaymentCard {
    @CreditCardNumber
    @NotNull(message = "Card number is required.")
    @Size(min=16, max=16,message = "Sixteen digits are required.")
    @Pattern(regexp="(^$|[0-9]+)", message = "Only integer numbers are acceptable.")
    private String cardNumber;

    @NotNull(message = "CVC code is required.")
    @Size(min=3, max=3,message = "Three digits are required.")
    @Pattern(regexp="(^$|[0-9]+)",message = "Only integer numbers are acceptable.")
    private String cvcCode;

    @NotNull(message = "Expiration date is required.")
    @Past
    private SimpleDateFormat expirationDate;

    @Column(length = 50, nullable = false)
    @NotNull(message = "Full name is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must be alphabetic.")
    private String name;
}
