package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.info("Fetching all firestations");
        return firestationRepository.findAll();
    }

    public Firestation addFirestation(Firestation firestation) {
        firestationRepository.save(firestation);
        log.info("Firestation added successfully: {}", firestation);
        return firestation;
    }

    public boolean updateFirestation(String address, Firestation updatedFirestation) {
        log.info("Updating firestation at address: {}", address);
        Firestation firestation = firestationRepository.findByAddress(address);

        if (firestation != null) {
            firestation.setAddress(updatedFirestation.getAddress());
            firestation.setStation(updatedFirestation.getStation());
            log.info("Firestation updated: {}", updatedFirestation);
            return true;
        }
        log.warn("Firestation not found for update at address: {}", address);
        return false;
    }

    public boolean deleteFirestation(String address) {
        log.info("Deleting firestation at address: {}", address);
        Firestation firestation = firestationRepository.findByAddress(address);

        if (firestation != null) {
            firestationRepository.delete(firestation);
            log.info("Firestation deleted at address: {}", address);
            return true;
        }
        return false;
    }

    public List<String> getPhoneNumbersByFirestation(String station) {
        log.info("Fetching phone numbers for firestation: {}", station);
        List<Firestation> firestationList = firestationRepository.findByStation(station);
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
        log.info("Fetching firestation by address: {}", address);
        return firestationRepository.findByAddress(address);
    }

    public List<String> getAddressesByStation(String station) {
        log.info("Fetching addresses for firestation: {}", station);
        return firestationRepository.findByStation(station).stream()
                .map(Firestation::getAddress)
                .toList();
    }
}
