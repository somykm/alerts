package com.safetynet.alerts.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.alerts.domain.Person;

import java.util.List;

public class Wrapper {
    @JsonProperty("persons")
    private List<Person> persons;

    public Wrapper() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String toStrong() {
        return "Wrapper {" + "persons =" + persons + "}";
    }
}
