package com.maids.cc.library.controller;

import com.maids.cc.library.model.BorrowingRecord;
import com.maids.cc.library.services.BorrowingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BorrowingControllerTest {

    @InjectMocks
    private BorrowingController borrowingController;

    @Mock
    private BorrowingService borrowingService;

    private BorrowingRecord borrowingRecord;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);
     
    }

    @Test
    public void testBorrowBook() {
        Long bookId = 1L;
        Long patronId = 1L;
        when(borrowingService.borrowBook(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> result = borrowingController.borrowBook(bookId, patronId);

        assertEquals(borrowingRecord, result);
    }

    @Test
    public void testReturnBook() {
        Long bookId = 1L;
        Long patronId = 1L;
        when(borrowingService.returnBook(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> result = borrowingController.returnBook(bookId, patronId);

        assertEquals(borrowingRecord, result);
    }
}
