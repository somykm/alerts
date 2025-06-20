package com.safetynet.alerts.repository;

import com.safetynet.alerts.domain.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {
    private final JsonParser jsonParser;

    @Autowired
    public MedicalRecordRepository(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public List<MedicalRecord> findAll() {
        return jsonParser.getAllMedicalRecords();
    }

    public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName) {
        for (MedicalRecord medicalRecord : jsonParser.getAllMedicalRecords()) {
            if (medicalRecord.getFirstName().equalsIgnoreCase(firstName) &&
                    medicalRecord.getLastName().equalsIgnoreCase(lastName)) {
                return medicalRecord;
            }
        }
        return null;
    }

    public void save(MedicalRecord medicalRecord) {
        jsonParser.getAllMedicalRecords().add(medicalRecord);
    }

    public void delete(MedicalRecord medicalRecord) {
        List<MedicalRecord> records = jsonParser.getAllMedicalRecords();
        records.remove(medicalRecord);
    }

    public void saveAll(List<MedicalRecord> medicalRecordList) {
        jsonParser.getAllMedicalRecords().addAll(medicalRecordList);
    }

    public MedicalRecord findByLastName(String lastName) {
        for(MedicalRecord medicalRecord: jsonParser.getAllMedicalRecords()){
            if(medicalRecord.getLastName().equalsIgnoreCase(lastName)){
                return medicalRecord;
            }
        }
        return null;
    }
}
