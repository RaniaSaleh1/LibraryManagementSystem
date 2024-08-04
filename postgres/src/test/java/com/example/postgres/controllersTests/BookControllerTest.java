package com.example.postgres.controllersTests;

import com.example.postgres.controller.BookController;
import com.example.postgres.service.BookService;
import entity.Book;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class BookControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Sample Book"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Sample Book");

        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Sample Book"));
    }

    // Additional tests for POST, PUT, DELETE, etc.
}
