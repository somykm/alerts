//package com.safetynet.alerts.repository;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.safetynet.alerts.domain.Firestation;
//import org.springframework.stereotype.Repository;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.ScopedValue;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class FirestationRepository {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final String filePath = "data.json";
//
//    public List<Firestation> findAll() {
//        try {
//            return objectMapper.readValue(new File(filePath), List.class);
//        } catch (IOException e) {
//            return new ArrayList<>();
//        }
//    }
//
//    public Firestation save(Firestation firestation) {
//        List<Firestation> firestations = findAll();
//        firestations.add(firestation);
//        writeToFile(firestations);
//        return firestation;
//    }
//
//
//    private void writeToFile(List<Firestation> firestations) {
//        try {
//            objectMapper.writeValue(new File(filePath), firestations);
//        } catch (IOException e) {
//            e.printStackTrace(); // Log the error instead of ignoring
//        }
//    }
//
//    public Firestation findByAddress(String address) {
//        return findAll().stream()
//                .filter(firestation -> firestation.getAddress().equals(address))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public void deleteByAddress(String address) {
//        List<Firestation> firestations = findAll().stream()
//                .filter(fs -> !fs.getAddress().equals(address))
//                .collect(Collectors.toList());
//        writeToFile(firestations);
//    }
//}
//
