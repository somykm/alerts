package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    private final FirestationRepository firestationRepository;
    private final PersonRepository personRepository;

    @Autowired
    public FirestationService(FirestationRepository firestationRepository, PersonRepository personRepository) {
        this.firestationRepository = firestationRepository;
        this.personRepository = personRepository;
    }

    public List<Firestation> getAllFireStations() {
        return firestationRepository.findAll();
    }

    public Firestation addFirestation(Firestation firestation) {
        firestationRepository.save(firestation);
        System.out.println("Firestation added successfully:" + firestation);
        return firestation;
    }

    public boolean updateFirestation(String address, Firestation updatedFirestation) {
        Firestation firestation = firestationRepository.findByAddress(address);

        if (firestation != null) {
            firestation.setAddress(updatedFirestation.getAddress());
            firestation.setStation(updatedFirestation.getStation());
            return true;
        }
        return false;
    }

    public boolean deleteFirestation(String address) {
        Firestation firestation = firestationRepository.findByAddress(address);

        if (firestation != null) {
            firestationRepository.delete(firestation);
            return true;
        }
        return false;
    }

    public List<String> getPhoneNumbersByFirestation(String station) {
        List<Firestation> firestationList = firestationRepository.findByStation(station);
        //get addresse from firestation list
        List<String> addresses = firestationList.stream()
                .map(Firestation::getAddress)
                .toList();
        List<Person> residents = personRepository.findByAddressIn(addresses);

        return residents.stream()
                .map(Person::getPhone)
                .distinct()
                .toList();
    }

    public Firestation getFirestationByAddress(String address) {
        return firestationRepository.findByAddress(address);
    }
//For method in FirestationArea
    public List<String> getAddressesByStation(String station) {
        return firestationRepository.findByStation(station).stream()
                .map(Firestation::getAddress)
                .toList();
    }
}
