package com.rental.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


@MappedSuperclass
@Access(AccessType.FIELD)
@Embeddable
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected UUID id;

    @Version
    @NotNull
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    protected long version;
}