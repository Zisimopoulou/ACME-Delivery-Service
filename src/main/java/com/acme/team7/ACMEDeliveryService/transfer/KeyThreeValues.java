package com.acme.team7.ACMEDeliveryService.transfer;

import lombok.Value;

@Value
public class KeyThreeValues<K,V,N,M> {
    K key;
    V firstValue;
    N secondValue;
    M thirdValue;
}
