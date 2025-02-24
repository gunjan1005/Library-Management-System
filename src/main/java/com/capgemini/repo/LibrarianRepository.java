package com.capgemini.repo;

import com.capgemini.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository <Librarian, Long> {

    public Librarian findByUsername(String username);
}
