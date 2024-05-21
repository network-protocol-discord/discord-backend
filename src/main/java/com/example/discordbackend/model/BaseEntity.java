package com.example.discordbackend.model;

import java.io.Serializable;

public interface BaseEntity<T> extends Serializable {

    T getId();
}
