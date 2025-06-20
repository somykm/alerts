package com.safetynet.alerts.domain;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfolastName {
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;
}
