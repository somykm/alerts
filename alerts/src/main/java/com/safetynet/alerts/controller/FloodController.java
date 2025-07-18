package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Flood;
import com.safetynet.alerts.service.FloodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/flood")
public class FloodController {
    private final FloodService floodService;

    @Autowired
    public FloodController(FloodService floodService) {
        this.floodService = floodService;
    }

    @GetMapping("/{stations}")
    public Map<String, List<Flood>> getAllHouseholdServedByFirestation(@RequestParam List<String> stations) {
        return floodService.getAllHouseholdByFirestation(stations);
    }

}
