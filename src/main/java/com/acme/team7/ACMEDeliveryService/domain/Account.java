package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS")
    @SequenceGenerator(name = "idGenerator", sequenceName = "ACCOUNTS_SEQ", initialValue = 1, allocationSize = 1)
public class Account extends BaseModel{
    @Column(length = 30, nullable = false)
    private String firstName;

    @Column(length = 30, nullable = false)
    private String lastName;

    @Column(length = 30, nullable = false)
    private String address;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false, unique = true)
    private String phoneNumber;

    @Column(length = 3)
    private int age;
}