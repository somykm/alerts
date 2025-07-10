package com.safetynet.alerts.domain;

import lombok.Data;

import java.util.List;

@Data
public class FirestationArea {
    private List<Person> residents;
    private int adultCount;
    private int childCount;
}
