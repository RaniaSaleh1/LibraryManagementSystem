package com.example.postgres.bookreview.api.repository;

import com.example.postgres.repositories.PatronRepository;
import entity.Patron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PatronRepositoryTest {

    @Autowired
    private PatronRepository patronRepository;

    @Test
    public void testSaveAndFindById() {
        // Arrange
        Patron patron = new Patron();
        patron.setName("John Doe");
        patron.setEmail("john.doe@example.com");
        patron.setPhoneNumber("123-456-7890");

        // Act
        Patron savedPatron = patronRepository.save(patron);
        Optional<Patron> foundPatron = patronRepository.findById(savedPatron.getId());

        // Assert
        assertTrue(foundPatron.isPresent());
        assertEquals("John Doe", foundPatron.get().getName());
    }

    @Test
    public void testFindAll() {
        // Act
        Iterable<Patron> patrons = patronRepository.findAll();

        // Assert
        assertThat(patrons).isNotNull();
        assertThat(patrons).hasSizeGreaterThan(0);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Patron patron = new Patron();
        patron.setName("Jane Smith");
        patron.setEmail("jane.smith@example.com");
        patron.setPhoneNumber("098-765-4321");
        patron = patronRepository.save(patron);

        // Act
        patronRepository.deleteById(patron.getId());
        Optional<Patron> deletedPatron = patronRepository.findById(patron.getId());

        // Assert
        assertThat(deletedPatron).isEmpty();
    }
}