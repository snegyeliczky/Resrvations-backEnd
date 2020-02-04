package com.codecool.reservationsbackend.entity;

public enum Roles {
    ADMIN("Admin"),
    SLAVE("Slave");


    private String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
