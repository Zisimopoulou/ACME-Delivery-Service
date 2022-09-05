package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must be alphabetic")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Column(length = 30, nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must be alphabetic")
    private String lastName;

    @NotNull(message = "Address is required")
    @Column(length = 50, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Address must be alphanumeric.")
    private String address;

    @NotNull(message = "Email number is required")
    @Column(length = 30, nullable = false, unique = true)
    @Email
    private String email;

    @NotNull(message = "Password is required")
    @Column(length = 30, nullable = false)
    @Pattern(regexp = "/^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$/gm" +
          "message = \"Invalid Password pattern" +
          ". Password must at least 8 characters of which one is digit" +
          ", one character and one special character.\"")
    private String password;

    @NotNull(message = "Phone number is required")
    @Column(length = 10, nullable = false, unique = true)
    @Pattern(regexp = "^\\\\d{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotNull(message = "Age is required.")
    @Min(value = 13, message = "too young for this application")
    private Integer age;
}

