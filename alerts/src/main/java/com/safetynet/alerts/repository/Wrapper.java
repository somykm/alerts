package com.safetynet.alerts.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Person;

import java.util.List;

public class Wrapper {
    @JsonProperty("persons")
    private List<Person> person;
    @JsonProperty("firestations")
    private List<Firestation> firestations;

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
    public List<Firestation> getFirestations(){
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations){
        this.firestations = firestations;
    }

}
