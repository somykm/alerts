package com.safetynet.alerts.repository;

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
}


