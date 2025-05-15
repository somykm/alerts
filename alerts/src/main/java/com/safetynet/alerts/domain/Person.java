package com.safetynet.alerts.domain;

import lombok.Data;

@Data
public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    public Person(String jane, String doe, String s, String springfield, String number, String s1, String mail) {
    }
}
