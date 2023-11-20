package com.ademy.XYZSERVICE.config;

import com.ademy.XYZSERVICE.model.HashedValue;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.Serializer;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;

public class HashedValueSerializer implements StreamSerializer<HashedValue> {
    @Override
    public void write(ObjectDataOutput objectDataOutput, HashedValue hashedValue) throws IOException {
        objectDataOutput.writeString(hashedValue.getRandom_key());
        objectDataOutput.writeString(hashedValue.getHashed_value());
    }

    @Override
    public HashedValue read(ObjectDataInput objectDataInput) throws IOException {
        return HashedValue.builder()
                .random_key(objectDataInput.readString())
                .hashed_value(objectDataInput.readString())
                .build();
    }

    @Override
    public int getTypeId() {
        return 1;
    }
}
