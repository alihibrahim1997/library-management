package com.maids.cc.library.controller;


import com.maids.cc.library.model.Patron;
import com.maids.cc.library.services.PatronService;
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
import static org.mockito.Mockito.*;

public class PatronControllerTest {

    @InjectMocks
    private PatronController patronController;

    @Mock
    private PatronService patronService;

    private Patron patron;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");
    }

    @Test
    public void testGetAllPatrons() {
        List<Patron> patrons = new ArrayList<>();
        patrons.add(patron);
        when(patronService.findAll()).thenReturn(patrons);

        List<Patron> result = (List<Patron>) patronController.getAllPatrons();
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    public void testGetPatronById() {
        when(patronService.findById(1L)).thenReturn(Optional.of(patron));

        ResponseEntity<Patron> response = patronController.getPatronById(1L);
        assertEquals(ResponseEntity.ok(patron), response);
    }

    @Test
    public void testAddPatron() {
        when(patronService.save(patron)).thenReturn(patron);

        ResponseEntity<Patron> result = patronController.addPatron(patron);
        assertEquals("John Doe", result.getBody().getName());
    }

    @Test
    public void testUpdatePatron() {
        when(patronService.update(1L, patron)).thenReturn(Optional.of(patron).get());

        ResponseEntity<Patron> response = patronController.updatePatron(1L, patron);
        assertEquals(ResponseEntity.ok(patron), response);
    }

}
