package com.example.postgres.vo;

import com.example.postgres.repositories.UserRepository;
import com.example.postgres.service.BookService;
import entity.Book;
import entity.Role;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class LibraryManagementSystemApplication {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialCreate() {
        return (args) -> {

            var book = new Book("9780134685991", "Effective Java", "Programming Series", "A comprehensive guide to best practices in Java programming.");

            bookService.saveBook(book);

            var book1 = new Book("BP567#R", "Spring Microservices", "KCXEF12389", "Description1");

            bookService.saveBook(book1);

            var book2 = new Book("GH67F#", "Spring Boot", "UV#JH", "description2");

            bookService.saveBook(book2);

            var user = new User("admin", "admin", "admin@admin.in", passwordEncoder.encode("Temp123"),
                    Arrays.asList(new Role("ROLE_ADMIN")));
            userRepository.save(user);

        };
    }
}
