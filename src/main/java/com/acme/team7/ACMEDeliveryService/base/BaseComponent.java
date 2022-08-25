package com.acme.team7.ACMEDeliveryService.base;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public abstract class BaseComponent {
    @PostConstruct
    public void init() {
        log.trace("Loaded {}.", getClass());
    }

    @PreDestroy
    public void destroy() {
        log.trace("{} is about to be destroyed.", getClass().getName());
    }

}
