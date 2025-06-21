package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.Fire;
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
    public List<Fire> getResidentAndStationByAddress(@RequestParam List<String> address) {
        log.info("Fetching residents data by address" + address);
        return fireService.getResidentAndStationByAddress(address);
    }
}
