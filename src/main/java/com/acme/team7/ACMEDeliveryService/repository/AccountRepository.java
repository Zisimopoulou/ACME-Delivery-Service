package com.acme.team7.ACMEDeliveryService.repository;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
    Account findByPhoneNumber(String phoneNumber);
}
