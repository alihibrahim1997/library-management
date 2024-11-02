package com.maids.cc.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maids.cc.library.model.Book;
import com.maids.cc.library.model.BorrowingRecord;
import com.maids.cc.library.model.Patron;
import com.maids.cc.library.repository.BorrowingRecordRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookService.findById(bookId).orElseThrow();
        Patron patron = patronService.findById(patronId).orElseThrow();

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies to borrow");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(LocalDateTime.now());
        record.setStatus("Borrowed");
        return borrowingRecordRepository.save(record);
    }

    public BorrowingRecord returnBook(Long bookId, Long patronId
    		) {
        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);
        if (record == null) {
            throw new RuntimeException("No active borrowing record found for this book and patron");
        }

        record.setReturnDate(LocalDateTime.now());
        record.setStatus("Returned");
        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return borrowingRecordRepository.save(record);
    }
}
