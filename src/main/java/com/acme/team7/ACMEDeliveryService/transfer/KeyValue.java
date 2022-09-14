package com.acme.team7.ACMEDeliveryService.transfer;

import lombok.Value;

@Value
public class KeyValue<K, V> {
    K key;
    V value;
}