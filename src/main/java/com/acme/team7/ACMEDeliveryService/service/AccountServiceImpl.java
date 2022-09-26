package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{
    private final AccountRepository accountRepository;
    @Override
    public JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public Account findByEmail(final String email) {
        return accountRepository.findByEmail(email);
    }
    @Override
    public Account findByPhoneNumber(final String phoneNumber) {
        return accountRepository.findByPhoneNumber(phoneNumber);
    }
}
