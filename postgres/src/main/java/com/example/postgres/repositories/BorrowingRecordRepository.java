package com.example.postgres.repositories;

import entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);
}
