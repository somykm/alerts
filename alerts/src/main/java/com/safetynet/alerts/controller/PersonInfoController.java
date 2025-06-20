package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.domain.PersonInfolastName;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.PersonInfoService;
import com.safetynet.alerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personInfolastName")
public class PersonInfoController {
    private final PersonInfoService personInfoService;


    @Autowired
    public PersonInfoController(PersonInfoService personInfoService) {
        super();
        this.personInfoService = personInfoService;
    }

    @GetMapping("")
    public List<PersonInfolastName> getPersonInfoByLastName(@RequestParam String lastName) {
        return personInfoService.getPersonInfoByLastName(lastName);
    }
}
