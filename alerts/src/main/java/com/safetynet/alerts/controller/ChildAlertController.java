package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.ChildAlert;
import com.safetynet.alerts.service.ChildAlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*http://localhost:8080/childAlert?address=<address>
This URL must return a list of children (any individual aged 18 years or younger) living
at this address. The list must include the first name, last name of each child,
age, and a list of other household members. If no children are found, this URL may
return an empty string.
*/
@Slf4j
@RestController
@Controller("/childAlert")
public class ChildAlertController {
    private final ChildAlertService childAlertService;

    @Autowired
    public ChildAlertController(ChildAlertService childAlertService){
        this.childAlertService= childAlertService;
    }

    @GetMapping("/{address}")
    public List<ChildAlert> getChildInfoByAddress(@RequestParam String address){
        return childAlertService.getChildInfoByAddress(address);
    }
}
