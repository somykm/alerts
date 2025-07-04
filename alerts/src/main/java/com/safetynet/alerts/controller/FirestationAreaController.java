package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.FirestationArea;
import com.safetynet.alerts.service.FirestationAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/FirestationArea")
public class FirestationAreaController {

    private final FirestationAreaService firestationAreaService;

    @Autowired
    public FirestationAreaController(FirestationAreaService firestationAreaService) {
        super();
        this.firestationAreaService = firestationAreaService;
    }

    @GetMapping("/{station}")
    public FirestationArea getResidentInfoByStation(@PathVariable String station){
            return firestationAreaService.getResidentInfoByStation(station);
    }
}
