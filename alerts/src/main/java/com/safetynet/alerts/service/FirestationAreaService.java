package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.FireResidents;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.FirestationArea;
import com.safetynet.alerts.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationAreaService {

    private final FireService fireService;
    private final FirestationService firestationService;

    @Autowired
    public FirestationAreaService(FireService fireService, FirestationService firestationService) {
        super();
        this.fireService = fireService;
        this.firestationService = firestationService;
    }

    public FirestationArea getResidentInfoByStation(String station) {
        List<String> addresses = firestationService.getAddressesByStation(station);
        List<FireResidents> residentsInfo = fireService.getResidentAndStationByAddress(addresses);

        List<Person> residentsData = new ArrayList<>();
        int adultCount = 0;
        int childCount = 0;

        for (FireResidents resident : residentsInfo) {
            Person person = new Person();
            person.setFirstName(resident.getFirstName());
            person.setLastName(resident.getLastName());
            person.setAddress(resident.getAddress());
            person.setPhone(resident.getPhone());

            residentsData.add(person);

            if (resident.getAge() <= 18) {
                childCount++;
            } else {
                adultCount++;
            }


        }
        FirestationArea response = new FirestationArea();
        response.setResidents(residentsData);
        response.setAdultCount(adultCount);
        response.setChildCount(childCount);

        return response;
    }

}

