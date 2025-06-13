package com.safetynet.alerts.domain;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfoDTO {
    private String firstName;
    private String address;
    private String age;
    private String email;
    private List<String> medicalHistory;
}
