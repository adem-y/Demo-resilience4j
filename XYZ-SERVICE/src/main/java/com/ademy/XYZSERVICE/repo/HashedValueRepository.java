package com.ademy.XYZSERVICE.repo;

import com.ademy.XYZSERVICE.model.HashedValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HashedValueRepository extends JpaRepository<HashedValue, Long> {
    @Query("SELECT hashed_value FROM HashedValue t WHERE t.random_key=?1")
    String getHashedValueByKey(String key);
}
