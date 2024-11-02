package com.maids.cc.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maids.cc.library.model.Patron;
import com.maids.cc.library.repository.PatronRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> findAll() {
        return patronRepository.findAll();
    }

    public Optional<Patron> findById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron save(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron update(Long id, Patron updatedPatron) {
        Patron patron = patronRepository.findById(id).orElseThrow();
        patron.setName(updatedPatron.getName());
        patron.setEmail(updatedPatron.getEmail());
        return patronRepository.save(patron);
    }

    public void deleteById(Long id) {
        patronRepository.deleteById(id);
    }
}
