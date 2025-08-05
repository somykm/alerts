package com.safetynet.alerts.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "release-notes")
public class ReleaseNotesEndpoint {

    String version1 = "**1.0** \n\n"
            + "* get person information added \n"
            + "* fire station added \n"
            + "* medical records added \n";

    @ReadOperation
    public String releaseNotes() {
        return version1;
    }
}
