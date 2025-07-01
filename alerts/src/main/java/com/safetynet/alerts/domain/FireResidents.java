package com.safetynet.alerts.domain;

import lombok.Data;

import java.util.List;
@Data
public class FireResidents {
    private String station;
    private String address;
    private String firstName;
    private String lastName;
    private String phone;

    private int age;
    private List<String> medications;
    private List<String> allergies;
}
