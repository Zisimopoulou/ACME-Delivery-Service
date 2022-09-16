package com.acme.team7.ACMEDeliveryService.transfer;

import lombok.Value;

@Value
public class KeyTwoValues<K,V,N> {
    K key;
    V firstValue;
    N secondValue;
}
