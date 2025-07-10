package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j //for using log
@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private PersonService personService;

    @Autowired
    public CommunityEmailController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/emails")
    public List<String> getEmailsByCity(@RequestParam String city) {
        log.info("Fetching emails by city" + city);
        return personService.getEmailsByCity(city);
    }
}
