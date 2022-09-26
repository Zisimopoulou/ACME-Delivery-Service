package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.service.AccountService;
import com.acme.team7.ACMEDeliveryService.service.BaseService;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountControllerImpl extends BaseControllerImpl<Account> {
    private final AccountService accountService;

    @Override
    public BaseService<Account> getBaseService() {
        return accountService;
    }

    @GetMapping(params = "email")
    public ResponseEntity<ApiResponse<Account>> findByEmail(@RequestParam String email) {
        final Account byEmail = accountService.findByEmail(email);
        if (byEmail == null) {
            throw new NoSuchElementException("Email not found");
        }
        return ResponseEntity.ok(ApiResponse.<Account>builder().data(byEmail).build());
    }

    @GetMapping(params = "phoneNumber")
    public ResponseEntity<ApiResponse<Account>> findPhoneNumber(@RequestParam String phoneNumber) {
        final Account byPhoneNumber = accountService.findByEmail(phoneNumber);
        if (byPhoneNumber == null) {
            throw new NoSuchElementException("Phone not found");
        }
        return ResponseEntity.ok(ApiResponse.<Account>builder().data(byPhoneNumber).build());
    }
}
