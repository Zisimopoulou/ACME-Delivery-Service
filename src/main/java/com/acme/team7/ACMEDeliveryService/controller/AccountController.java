package com.acme.team7.ACMEDeliveryService.controller;

import com.acme.team7.ACMEDeliveryService.domain.Account;
import com.acme.team7.ACMEDeliveryService.transfer.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface AccountController extends BaseController<Account> {
    ResponseEntity<ApiResponse<Account>> findByEmail(@RequestParam String email);
    ResponseEntity<ApiResponse<Account>> findPhoneNumber(@RequestParam String phoneNumber);
}
