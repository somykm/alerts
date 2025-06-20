package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.PersonInfolastName;
import com.safetynet.alerts.service.PersonInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
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
        log.info("Fetching person data by last name" + lastName);
        return personInfoService.getPersonInfoByLastName(lastName);
    }
}
