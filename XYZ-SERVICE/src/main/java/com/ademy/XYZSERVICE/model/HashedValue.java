package com.ademy.XYZSERVICE.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hashed_value_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HashedValue {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "random_key")
    private String random_key;

    @Column(name = "hashed_value")
    private String hashed_value;
}
