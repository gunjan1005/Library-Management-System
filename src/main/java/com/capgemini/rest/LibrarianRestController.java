package com.capgemini.rest;

import com.capgemini.entity.Librarian;
import com.capgemini.repo.LibrarianRepository;
import com.capgemini.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LibrarianRestController {

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/welcome")
    public String getWelcomeMsg(){

        return "Welcome to Library System";
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveLibrarian(@RequestBody Librarian librarian){

              String encodePwd = pwdEncoder.encode(librarian.getPassword());
              librarian.setPassword(encodePwd);

              librarianRepository.save(librarian);

              return  new ResponseEntity<>("Librarian Registered", HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestBody Librarian librarian){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(librarian.getUsername(),librarian.getPassword());

        try{

            Authentication authentication = authManager.authenticate(token);

            if(authentication.isAuthenticated()){

                String jwt = jwtService.generateToken(librarian.getUsername());

                return new ResponseEntity<>(jwt,HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return  new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }
}
