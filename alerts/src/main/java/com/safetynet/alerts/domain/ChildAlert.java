package com.safetynet.alerts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChildAlert {
    private String firstName;
    private String lastName;
    private int age;
    private List<String> householdMembers;
}
