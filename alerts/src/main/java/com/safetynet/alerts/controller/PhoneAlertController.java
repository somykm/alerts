package com.safetynet.alerts.controller;

import com.safetynet.alerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {
    private FirestationService firestationService;

    @Autowired
    public PhoneAlertController(FirestationService firestationService){
        this.firestationService = firestationService;
    }

    @GetMapping("/{phone}")
    public List<String> getPhoneNumberByFirestation(@RequestParam String firestation){
        return firestationService.getPhoneNumbersByFirestation(firestation);
    }
}
