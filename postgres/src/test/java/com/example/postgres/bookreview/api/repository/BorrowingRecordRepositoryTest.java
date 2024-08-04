package com.example.postgres.bookreview.api.repository;

import com.example.postgres.repositories.BookRepository;
import com.example.postgres.repositories.BorrowingRecordRepository;
import com.example.postgres.repositories.PatronRepository;
import entity.Book;
import entity.BorrowingRecord;
import entity.Patron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BorrowingRecordRepositoryTest {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Test
    public void testSaveAndFindById() {
        // Arrange
        Book book = new Book();
        book.setTitle("Domain-Driven Design");
        book.setAuthor("Eric Evans");
        book.setIsbn("9780321125217");
        book.setPublicationYear(2004);
        book = bookRepository.save(book);

        Patron patron = new Patron();
        patron.setName("Alice Johnson");
        patron.setEmail("alice.johnson@example.com");
        patron.setPhoneNumber("321-654-0987");
        patron = patronRepository.save(patron);

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowingDate(LocalDate.now());
        record.setReturnDate(LocalDate.now().plusWeeks(2));

        // Act
        BorrowingRecord savedRecord = borrowingRecordRepository.save(record);
        Optional<BorrowingRecord> foundRecord = borrowingRecordRepository.findById(savedRecord.getId());

        // Assert
        assertTrue(foundRecord.isPresent());
        assertEquals(savedRecord.getId(), foundRecord.get().getId());
        assertEquals("Domain-Driven Design", foundRecord.get().getBook().getTitle());
        assertEquals("Alice Johnson", foundRecord.get().getPatron().getName());
    }

    @Test
    public void testFindAll() {
        // Act
        Iterable<BorrowingRecord> records = borrowingRecordRepository.findAll();

        // Assert
        assertThat(records).isNotNull();
        assertThat(records).hasSizeGreaterThan(0);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Book book = new Book();
        book.setTitle("Clean Architecture");
        book.setAuthor("Robert C. Martin");
        book.setIsbn("9780134494166");
        book.setPublicationYear(2017);
        book = bookRepository.save(book);

        Patron patron = new Patron();
        patron.setName("Bob Anderson");
        patron.setEmail("bob.anderson@example.com");
        patron.setPhoneNumber("789-456-1230");
        patron = patronRepository.save(patron);

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowingDate(LocalDate.now());
        record.setReturnDate(LocalDate.now().plusWeeks(2));
        record = borrowingRecordRepository.save(record);

        // Act
        borrowingRecordRepository.deleteById(record.getId());
        Optional<BorrowingRecord> deletedRecord = borrowingRecordRepository.findById(record.getId());

        // Assert
        assertThat(deletedRecord).isEmpty();
    }
}
