package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getAllMedicalRecords() {

        return medicalRecordRepository.findAll();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
        System.out.println("Medical record added: " + medicalRecord);
        return medicalRecord;
    }

    public boolean updateMedicalRecord(String firstName, String lastName, MedicalRecord updatedRecord) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);

        if (medicalRecord != null) {
            medicalRecord.setBirthdate(updatedRecord.getBirthdate());
            medicalRecord.setMedications(updatedRecord.getMedications());
            medicalRecord.setAllergies(updatedRecord.getAllergies());
            return true;
        }
        return false;
    }

    public boolean deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);

        if (medicalRecord != null) {
            medicalRecordRepository.delete(medicalRecord);
            return true;
        }
        return false;
    }
}
