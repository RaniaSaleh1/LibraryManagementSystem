package com.example.postgres.bookreview.api.repository;

import com.example.postgres.repositories.BookRepository;
import entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vo.LibraryManagementSystemApplication;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = LibraryManagementSystemApplication.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveAndFindById() {
        // Arrange
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setIsbn("9780134685991");
        book.setPublicationYear(2018);

        // Act
        Book savedBook = bookRepository.save(book);
        Optional<Book> foundBook = bookRepository.findById(savedBook.getId());

        // Assert
        assertTrue(foundBook.isPresent());
        assertEquals("Effective Java", foundBook.get().getTitle());
    }

    @Test
    public void testFindAll() {
        // Act
        Iterable<Book> books = bookRepository.findAll();

        // Assert
        assertThat(books).isNotNull();
        assertThat(books).hasSizeGreaterThan(0);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Book book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setIsbn("9780132350884");
        book.setPublicationYear(2008);
        book = bookRepository.save(book);

        // Act
        bookRepository.deleteById(book.getId());
        Optional<Book> deletedBook = bookRepository.findById(book.getId());

        // Assert
        assertThat(deletedBook).isEmpty();
    }
}

