package com.safetynet.alerts.actuator;

import com.safetynet.alerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    private PersonService personService;

    @Autowired
    public HealthCheck(PersonService personService){
    super();
    this.personService = personService;
    }

    @Override
    public Health health() {
        if(personService.getAllPeople("Up")==null){
            return Health.down().withDetail("Cause", "OMDV API is not available").build();
        }
        return Health.up().build();
    }
}
