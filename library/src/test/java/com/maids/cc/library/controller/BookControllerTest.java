package com.maids.cc.library.controller;

import com.maids.cc.library.model.Book;
import com.maids.cc.library.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(book);
        when(bookService.findAll()).thenReturn(books);

        List<Book> result = bookController.getAllBooks();
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
    }

    @Test
    public void testGetBookById() {
        when(bookService.findById(1L)).thenReturn(Optional.of(book));

        ResponseEntity<Book> response = bookController.getBookById(1L);
        assertEquals(ResponseEntity.ok(book), response);
    }

    @Test
    public void testAddBook() {
        when(bookService.save(book)).thenReturn(book);

        Book result = bookController.createBook(book);
        assertEquals("Test Book", result.getTitle());
    }

    @Test
    public void testUpdateBook() {
        when(bookService.update(1L, book)).thenReturn(Optional.of(book).get());

        ResponseEntity<Book> response = bookController.updateBook(1L, book);
        assertEquals(ResponseEntity.ok(book), response);
    }


}
