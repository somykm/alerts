package com.safetynet.alerts.domain;

import lombok.Data;

@Data
public class Firestation {
    private String address;
    private String station;

    public Firestation(String s, String number) {
    }
}
