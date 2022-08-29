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
    @Column(length = 30, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$$", message = "Address must be alphabetic")
    private String address;

    @NotNull(message = "Email number is required")
    @Column(length = 30, nullable = false, unique = true)
    @Email
    private String email;

    @NotNull(message = "Password is required")
    @Column(length = 30, nullable = false)
    @Pattern(regexp = "\"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()Ã¢Â€Â“[{}]:;',?/*~$^+=<>]).{8,20}$\", \n" +
            "         message = \"Invalid Password pattern" +
            ". Password must contain 8 to 20 characters at least one digit" +
            ", lower, upper case and one special character.\"")
    private String password;

    @NotNull(message = "Phone number is required")
    @Column(length = 10, nullable = false, unique = true)
    private String phoneNumber;

    @Max(value = 140, message = "too old for this application")
    @Min(value = 13, message = "too young for this application")
    private Integer age;
}

