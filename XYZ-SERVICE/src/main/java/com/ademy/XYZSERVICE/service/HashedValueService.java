package com.ademy.XYZSERVICE.service;

public interface HashedValueService {

    void addValue(String key);
    String getValue(String key) throws InterruptedException;
    String getValueFromCache(String key);
}
