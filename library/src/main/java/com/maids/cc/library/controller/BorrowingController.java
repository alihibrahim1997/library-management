package com.maids.cc.library.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.maids.cc.library.model.BorrowingRecord;
import com.maids.cc.library.services.BorrowingService;

@RestController
@RequestMapping("/api")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId
) {
        BorrowingRecord record = borrowingService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId
    		
    		) {
        BorrowingRecord record = borrowingService.returnBook(bookId, patronId);
        return ResponseEntity.ok(record);
    }
}
