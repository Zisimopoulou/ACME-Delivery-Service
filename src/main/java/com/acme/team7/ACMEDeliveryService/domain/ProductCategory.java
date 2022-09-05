package com.acme.team7.ACMEDeliveryService.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@ToString(callSuper = true)
public enum ProductCategory {
    SALAD,
    APPETISERS,
    SOUVLAKI,
    ICECREAM,
    SAUCE,
    OVEN,
    ROASTMEAT,
    DRINKS,
    SINGLEPIECE,
    SANDWICH,
    SKEPASTES,
    POIKILIES
}
