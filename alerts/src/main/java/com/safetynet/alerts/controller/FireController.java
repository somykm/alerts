package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.FireResidents;
import com.safetynet.alerts.service.FireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Controller("/fire")
public class FireController {
    private final FireService fireService;

    @Autowired
    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping("/{address}")
    public List<FireResidents> getResidentAndStationByAddress(@RequestParam List<String> address) {
        log.info("Requesting to fetch fire station and residents for address: {}", address);

        List<FireResidents> residents = fireService.getResidentAndStationByAddress(address);

        if (residents.isEmpty()) {
            log.warn("No residents found for address: {}", address);
        } else {
            log.info("Fetched {} resident for address: {}", residents.size(), address);
        }

        return residents;
    }

}
