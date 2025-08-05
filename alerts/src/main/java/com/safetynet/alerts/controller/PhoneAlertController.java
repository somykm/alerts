package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {
    private FirestationService firestationService;

    @Autowired
    public PhoneAlertController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @GetMapping("")
    public List<String> getPhoneNumberByFirestation(@RequestParam String firestation) {
        log.info("Fetching phone alert by station number \n,return a list of phone numbers of residents served by the fire station" + firestation);
        return firestationService.getPhoneNumbersByFirestation(firestation);
    }
}
