package com.ademy.XYZSERVICE.service;

import com.ademy.XYZSERVICE.model.HashedValue;
import com.ademy.XYZSERVICE.repo.HashedValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class HashedValueServiceImpl implements HashedValueService {

    @Autowired
    private HashedValueRepository repository;

    public HashedValueServiceImpl(HashedValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addValue(String key) {
        HashedValue hashedValue = new HashedValue();
        hashedValue.setId(generateId());
        hashedValue.setRandom_key(key);
        hashedValue.setHashed_value(generateValue());
        repository.save(hashedValue);
    }

    private String generateValue() {
        return RandomStringUtils.randomAlphabetic(10).toUpperCase(Locale.ROOT);
    }

    private Long generateId() {
        return Long.valueOf(RandomStringUtils.randomNumeric(5));
    }

    @Override
    public String getValue(String key) {
        return repository.getHashedValueByKey(key);
    }
}
