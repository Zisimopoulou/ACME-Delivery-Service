package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Account;

public interface AccountService extends BaseService<Account> {
    Account findByEmail(String email);
    Account findByPhoneNumber(String phoneNumber);
}
