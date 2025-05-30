package com.safetynet.alerts.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.domain.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationRepository {
    private final JsonParser jsonParser;

    @Autowired
    public FirestationRepository(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public List<Firestation> findAll() {
        return jsonParser.getAllFirestation();
    }

    public void save(Firestation firestation) {
        jsonParser.getAllFirestation().add(firestation);
    }

    public Firestation findByAddress(String address) {
        for (Firestation firestation : jsonParser.getAllFirestation()) {
            if (firestation.getAddress().equalsIgnoreCase(address)) {
                return firestation;
            }
        }
        return null;
    }

    public void delete(Firestation firestation) {
        List<Firestation> firestations = jsonParser.getAllFirestation();
        firestations.remove(firestation);
    }

//    public void delete(String address) {
//
//        List<Firestation> firestations = jsonParser.getAllFirestation();
//        Firestation firestationToBeDelete = findByAddress(address);
//        if (firestationToBeDelete != null) {
//            firestations.remove(firestationToBeDelete);
//        }
//    }

    public void saveAll(List<Firestation> firestationList) {

    }
}


