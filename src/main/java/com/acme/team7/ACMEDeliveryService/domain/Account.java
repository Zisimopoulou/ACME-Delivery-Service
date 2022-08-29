package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS", indexes = {@Index(name = "ACCOUNT_IDX_01", columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "ACCOUNTS_SEQ", initialValue = 1, allocationSize = 1)
public class Account extends BaseModel{
    @Column(length = 30, nullable = false)
    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Column(length = 30, nullable = false)
    private String lastName;

    @NotNull(message = "Address is required")
    @Column(length = 30, nullable = false)
    private String address;

    @NotNull(message = "Email number is required")
    @Column(length = 30, nullable = false, unique = true)
    @Email
    private String email;

    @NotNull(message = "Password is required")
    @Column(length = 30, nullable = false)
    private String password;

    @NotNull(message = "Phone number is required")
    @Column(length = 10, nullable = false, unique = true)
    @NumberFormat(pattern = "69########")
    private String phoneNumber;

    @Max(value = 140, message = "too old for this application")
    @Min(value = 13, message = "too young for this application")
    private Integer age;
}

