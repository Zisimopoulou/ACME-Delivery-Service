package com.acme.team7.ACMEDeliveryService.transfer;

import lombok.Value;

@Value
public class ApiError {
    Integer status;
    String message;
    String path;
}
