package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;

import java.util.List;

public class Wrapper {
    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}
