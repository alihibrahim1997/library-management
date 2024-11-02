package com.maids.cc.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maids.cc.library.model.Patron;
@Repository
public interface PatronRepository extends JpaRepository<Patron, Long>{

}
