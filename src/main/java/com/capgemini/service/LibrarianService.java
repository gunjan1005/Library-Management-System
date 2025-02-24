package com.capgemini.service;

import com.capgemini.entity.Librarian;
import com.capgemini.repo.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LibrarianService implements UserDetailsService {

    @Autowired
    private LibrarianRepository librarianRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Librarian librarian  = librarianRepository.findByUsername(username);


        return new User(librarian.getUsername(),librarian.getPassword(), Collections.emptyList());
    }
}
