package com.safetynet.alerts.domain;

import lombok.Data;

import java.util.List;

@Data
public class Flood {
    private String address;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<String> houseHoldServedByStation;
    private List<String> medications;
    private List<String> allergies;
}
