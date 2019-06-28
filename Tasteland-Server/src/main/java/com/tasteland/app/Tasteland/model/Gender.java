package com.tasteland.app.Tasteland.model;

public enum Gender {

    M("Male"),
    F("Female");

    private String name;

     Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
