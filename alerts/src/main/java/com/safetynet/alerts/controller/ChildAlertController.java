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
@Controller("/childAlert")
public class ChildAlertController {
    private final ChildAlertService childAlertService;

    @Autowired
    public ChildAlertController(ChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }

    @GetMapping("")
    public List<ChildAlert> getChildInfoByAddress(@RequestParam String address) {
        return childAlertService.getChildInfoByAddress(address);
    }
}
