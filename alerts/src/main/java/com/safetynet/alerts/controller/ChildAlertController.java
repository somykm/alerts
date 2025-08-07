package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.ChildAlert;
import com.safetynet.alerts.service.ChildAlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/childAlert")
@Controller
public class ChildAlertController {
    private final ChildAlertService childAlertService;

    @Autowired
    public ChildAlertController(ChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }

    @GetMapping
    public List<ChildAlert> getChildInfoByAddress(@RequestParam String address) {
        log.info("Fetching child data by address: {}", address);

        try {
            log.debug("Calling service to get child info...");
            List<ChildAlert> children = childAlertService.getChildInfoByAddress(address);

            log.info("Found {} children at address: {}", children.size(), address);
            return children;
        } catch (Exception e) {
            log.error("Failed to get child info for address: {}", address, e);
            throw e;
        }
    }
}


