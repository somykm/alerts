package com.safetynet.alerts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FirestationArea {
    private List<Person> residents;
    private int adultCount;
    private int childCount;
}
