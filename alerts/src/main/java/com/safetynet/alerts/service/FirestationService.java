//package com.safetynet.alerts.service;
//
//import com.safetynet.alerts.domain.Firestation;
//import com.safetynet.alerts.repository.FirestationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FirestationService {
//
//    private final FirestationRepository firestationRepository;
//
//    @Autowired
//    public FirestationService(FirestationRepository firestationRepository) {
//        this.firestationRepository = firestationRepository;
//    }
//
//    public List<Firestation> getAllFireStations() {
//        return firestationRepository.findAll();
//    }
//
//    public Firestation getFireStationByAddress(String address) {
//        return firestationRepository.findByAddress(address);
//    }
//
//    public Firestation saveFireStation(Firestation fireStation) {
//        return firestationRepository.save(fireStation);
//    }
//
//    public void deleteFireStation(String address) {
//        firestationRepository.deleteByAddress(address);
//    }
//
//}
