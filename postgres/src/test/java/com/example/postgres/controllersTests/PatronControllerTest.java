package com.example.postgres.controllersTests;

import com.example.postgres.controller.PatronController;
import com.example.postgres.service.PatronService;
import entity.Patron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatronController.class)
public class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    @Test
    public void testGetAllPatrons() throws Exception {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");

        when(patronService.getAllPatrons()).thenReturn(Arrays.asList(patron));

        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    public void testGetPatronById() throws Exception {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");

        when(patronService.getPatronById(1L)).thenReturn(Optional.of(patron));

        mockMvc.perform(get("/api/patrons/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    // Additional tests for POST, PUT, DELETE, etc.
}
