package com.safetynet.alerts.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//repository allows you to retrieve and delete a person using  and  as identifiers.
@Repository
public class JsonParser {
    ObjectMapper objectMapper = new ObjectMapper();
    File jsonFile = new File("src/main/resources/data.json");
    List<Person> persons =new ArrayList<>();
    public JsonParser() throws IOException {
        loadData();
    }

    void loadData() throws IOException {
        persons =objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {});
    }
    List<Person> getAllPeople(){
        return persons;
    }
    void addPerson(Person person){
        persons.add(person);
        saveData();
    }

    void deletePerson(String firstName, String lastName){
        persons.removeIf(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName));
        saveData();
    }

    void saveData(){
        try{
            objectMapper.writeValue(jsonFile, persons);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
