package com.safetynet.alerts.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Wrapper {
    @JsonProperty("persons")
    private List<Person> person;
    @Getter
    @Setter
    @JsonProperty("firestations")
    private List<Firestation> firestations;
    @Getter
    @Setter
    @JsonProperty("medicalrecords")
    private List<MedicalRecord> medicalRecords;

    public Wrapper() {
    }

    public List<Person> getPersons() {
        return person;
    }

    public void setPersons(List<Person> person) {
        this.person = person;
    }

    public String toStrong() {
        return "Wrapper {" + "person =" + person + "}";
    }

}
