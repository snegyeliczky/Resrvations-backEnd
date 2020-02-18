package com.codecool.reservationsbackend.userservice.entity;

public enum Roles {
    ADMIN("ROLE_ADMIN"),
    RECEPTIONIST("ROLE_RECEPTIONIST");

    private String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
