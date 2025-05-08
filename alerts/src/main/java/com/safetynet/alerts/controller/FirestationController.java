//package com.safetynet.alerts.controller;
//
//import com.safetynet.alerts.domain.Firestation;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/firestation")
//public class FirestationController {
//        private final List<Firestation> firestations = new ArrayList<>();
//
//        @PostMapping
//        public ResponseEntity<String> addMapping(@RequestBody Firestation firestation) {
//            firestations.add(firestation);
//            return ResponseEntity.ok("Mapping added successfully!");
//        }
//
//        @PutMapping
//        public ResponseEntity<String> updateMapping(@RequestBody Firestation firestation) {
//            for (Firestation fs : firestations) {
//                if (fs.getAddress().equals(firestation.getAddress())) {
//                    fs.setFirestationNumber(firestation.getFirestationNumber());
//                    return ResponseEntity.ok("Mapping updated successfully!");
//                }
//            }
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found!");
//        }
//
//        @DeleteMapping("/{address}")
//        public ResponseEntity<String> deleteMapping(@PathVariable String address) {
//            firestations.removeIf(fs -> fs.getAddress().equals(address));
//            return ResponseEntity.ok("Mapping deleted successfully!");
//        }
//}
