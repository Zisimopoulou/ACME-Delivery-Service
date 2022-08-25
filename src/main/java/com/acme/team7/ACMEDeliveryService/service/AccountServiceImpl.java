package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.repository.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{
    private final AccountRepository accountRepository;
    public JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }
}
