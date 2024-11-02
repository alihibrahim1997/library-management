package com.maids.cc.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maids.cc.library.model.BorrowingRecord;
@Repository
public interface BorrowingRecordRepository  extends JpaRepository<BorrowingRecord, Long>{
    BorrowingRecord findByBookIdAndPatronIdAndReturnDateIsNull(Long bookId, Long patronId);

}
