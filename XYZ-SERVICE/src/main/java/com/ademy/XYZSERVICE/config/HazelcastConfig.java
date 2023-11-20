package com.ademy.XYZSERVICE.config;

import com.ademy.XYZSERVICE.model.HashedValue;
import org.springframework.stereotype.Component;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

@Component
public class HazelcastConfig {

    public static final String HASHED_VALUE = "hashed_value";
    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());

    public HashedValue put(String number, HashedValue student) {
        IMap<String, HashedValue> map = hazelcastInstance.getMap(HASHED_VALUE);
        return map.putIfAbsent(number, student);
    }

    public HashedValue get(String key) {
        IMap<String, HashedValue> map = hazelcastInstance.getMap(HASHED_VALUE);
        return map.get(key);
    }

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        config.getSerializationConfig().addSerializerConfig(serializerConfig());
        return config;
    }

    private SerializerConfig serializerConfig() {
        return new SerializerConfig().setImplementation(new HashedValueSerializer()).setTypeClass(HashedValue.class);
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(HASHED_VALUE);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(400);
        return mapConfig;
    }

}