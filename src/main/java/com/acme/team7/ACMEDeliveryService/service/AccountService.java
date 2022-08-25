package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.repository.AccountRepository;

public interface AccountService extends BaseService<Account> {
    public AccountRepository getRepository();
}
