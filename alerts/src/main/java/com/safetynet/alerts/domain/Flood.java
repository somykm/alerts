package com.safetynet.alerts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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
