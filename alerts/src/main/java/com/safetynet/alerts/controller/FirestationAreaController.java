package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.FirestationArea;
import com.safetynet.alerts.service.FirestationAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/firestationArea")
public class FirestationAreaController {
    private final FirestationAreaService firestationAreaService;

    @Autowired
    public FirestationAreaController(FirestationAreaService firestationAreaService) {
        super();
        this.firestationAreaService = firestationAreaService;
    }

    @GetMapping("/station")
    public FirestationArea getResidentInfoByStation(@RequestParam String station) {
        log.info("Received request to fetch resident info for fire station: {}", station);

        FirestationArea residentInfo = firestationAreaService.getResidentInfoByStation(station);

        if (residentInfo == null) {
            log.warn("No resident info found for this station: {}", station);
        } else {
            log.info("Successfully retrieved resident info for station: {}", station);
        }

        return residentInfo;
    }
}
