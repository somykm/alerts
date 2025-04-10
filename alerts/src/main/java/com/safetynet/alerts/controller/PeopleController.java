package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PeopleController {

    @GetMapping("/person")
    public List<Person> fetchPeople() {
        return new ArrayList<Person>();
    }
    //public ModelAndView getPeople(){

   // }
}
