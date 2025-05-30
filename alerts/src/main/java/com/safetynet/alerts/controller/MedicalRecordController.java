package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        super();
        this.medicalRecordService = medicalRecordService;
    }

    // 1. Fetch all medical records (GET)
    @GetMapping("/all")
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    // 2. Add a new medical record (POST)
    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }

    // 3. Update an existing medical record (PUT)
    @PutMapping("/{firstName}/{lastName}")
    public boolean updateMedicalRecord(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @RequestBody MedicalRecord updatedRecord) {
        return medicalRecordService.updateMedicalRecord(firstName, lastName, updatedRecord);
    }

    // 4. Delete a medical record (DELETE)
    @DeleteMapping("/{firstName}/{lastName}")
    public String deleteMedicalRecord(@PathVariable String firstName,
                                      @PathVariable String lastName) {
        boolean deleted = medicalRecordService.deleteMedicalRecord(firstName, lastName);
        return deleted ? "Medical record deleted successfully." : "Medical record not found!";
    }

}
