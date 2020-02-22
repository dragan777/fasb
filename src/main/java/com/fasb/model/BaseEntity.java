package com.fasb.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;


@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @NotNull
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }
}
