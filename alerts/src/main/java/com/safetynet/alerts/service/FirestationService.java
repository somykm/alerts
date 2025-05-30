package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    private final FirestationRepository firestationRepository;

    @Autowired
    public FirestationService(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
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
}
