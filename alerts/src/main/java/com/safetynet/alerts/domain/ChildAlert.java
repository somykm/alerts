package com.safetynet.alerts.domain;

import lombok.Data;

import java.util.List;

@Data
public class ChildAlert {
    private String firstName;
    private String lastName;
    private int age;
    private List<String> householdMembers;

//    public ChildAlert(String john, String doe, int i, List<String> janeDoe) {
//    }
}
