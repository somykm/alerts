package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        super();
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public List<MedicalRecord> getMedicalRecords() {
        log.info("Fetching all medical records");
        return medicalRecordService.getAllMedicalRecords();
    }

    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("Adding medical record for: {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }

    @PutMapping("/{firstName}/{lastName}")
    public boolean updateMedicalRecord(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @RequestBody MedicalRecord updatedRecord) {
        log.info("Updating medical record for: {} {}", firstName, lastName);
        return medicalRecordService.updateMedicalRecord(firstName, lastName, updatedRecord);
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public String deleteMedicalRecord(@PathVariable String firstName,
                                      @PathVariable String lastName) {
        log.info("Deleting medical record for: {} {}", firstName, lastName);
        boolean deleted = medicalRecordService.deleteMedicalRecord(firstName, lastName);
        return deleted ? "The person deleted successfully from Medical record list." : "Medical record not found!";
    }
}