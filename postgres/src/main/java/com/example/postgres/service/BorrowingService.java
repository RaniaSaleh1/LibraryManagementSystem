package com.example.postgres.service;

import com.example.postgres.repositories.BorrowingRecordRepository;
import entity.Book;
import entity.BorrowingRecord;
import entity.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        BorrowingRecord record = new BorrowingRecord();
        record.setBook(new Book(bookId));
        record.setPatron(new Patron(patronId));
        record.setBorrowingDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }

    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
        record.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }
}
