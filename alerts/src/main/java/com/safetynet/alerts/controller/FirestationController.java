package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/firestations")
public class FirestationController {
    private final FirestationService firestationService;

    @Autowired
    public FirestationController(FirestationService firestationService) {
        super();
        this.firestationService = firestationService;
    }

    @GetMapping("")
    public List<Firestation> getFirestationsList() {
        return firestationService.getAllFireStations();
    }

    @PostMapping
    public Firestation addFirestation(@RequestBody Firestation firestation) {
        return firestationService.addFirestation(firestation);
    }

    @PutMapping("/{address}")
    public boolean updateFirestation(@PathVariable String address,
                                     @RequestBody Firestation updatedFirestation) {

        return firestationService.updateFirestation(address, updatedFirestation);
    }

    @DeleteMapping("/{address}")
    public String deleteFirestation(@PathVariable String address) {
        boolean deleted = firestationService.deleteFirestation(address);
        if (deleted) {
            return "Firestation deleted successfully!";
        } else {
            return "Firestation not found!";
        }
    }
}



