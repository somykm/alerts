package com.safetynet.alerts.repository;

import com.safetynet.alerts.domain.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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
        return jsonParser.getAllFirestation().stream()
                .filter(f -> f.getAddress() != null && f.getAddress().equalsIgnoreCase(address))
                .findFirst()
                .orElse(null);
    }

    public void delete(Firestation firestation) {
        List<Firestation> firestations = jsonParser.getAllFirestation();
        firestations.remove(firestation);
    }

    public List<Firestation> findByStation(String station) {
        return jsonParser.getAllFirestation().stream()
                .filter(firestation -> firestation.getStation() != null && firestation.getStation() .equalsIgnoreCase(station))
                .collect(Collectors.toList());
    }
}


