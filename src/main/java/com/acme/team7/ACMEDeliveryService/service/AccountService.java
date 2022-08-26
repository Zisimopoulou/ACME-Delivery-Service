package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountService extends BaseService<Account> {
    public JpaRepository<Account, Long> getRepository();
}
