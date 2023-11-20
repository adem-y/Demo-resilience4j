package com.ademy.XYZSERVICE.service;

import com.ademy.XYZSERVICE.config.HazelcastConfig;
import com.ademy.XYZSERVICE.model.HashedValue;
import com.ademy.XYZSERVICE.repo.HashedValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class HashedValueServiceImpl implements HashedValueService {

    @Autowired
    private HashedValueRepository repository;

    private final HazelcastConfig hazelcastConfig;

    public HashedValueServiceImpl(HashedValueRepository repository, HazelcastConfig hazelcastConfig) {
        this.repository = repository;
        this.hazelcastConfig = hazelcastConfig;
    }

    @Override
    public void addValue(String key) {
        HashedValue hashedValue = new HashedValue();
        hashedValue.setId(generateId());
        hashedValue.setRandom_key(key);
        hashedValue.setHashed_value(generateValue());

        try {
            repository.save(hashedValue);
            log.info("Db save successful. Key: {} Value: {}", hashedValue.getRandom_key(), hashedValue.getHashed_value());
        } catch (Exception e) {
            log.error("Error while save data to db: " + e);
        } finally {
            hazelcastConfig.put(key, hashedValue);
            log.info("Cache save successful. Key: {} Value: {}", hashedValue.getRandom_key(), hashedValue.getHashed_value());
        }
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

    @Override
    public String getValueFromCache(String key) {
        return hazelcastConfig.get(key).getHashed_value();
    }
}
