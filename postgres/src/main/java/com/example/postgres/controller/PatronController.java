package com.example.postgres.controller;

import com.example.postgres.service.PatronService;
import entity.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patron = patronService.getPatronById(id);
        return patron.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patron addPatron(@RequestBody Patron patron) {
        return patronService.savePatron(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody Patron patronDetails) {
        Optional<Patron> patron = patronService.getPatronById(id);
        if (patron.isPresent()) {
            Patron updatedPatron = patron.get();
            updatedPatron.setName(patronDetails.getName());
            updatedPatron.setContactInformation(patronDetails.getContactInformation());
            return ResponseEntity.ok(patronService.savePatron(updatedPatron));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
