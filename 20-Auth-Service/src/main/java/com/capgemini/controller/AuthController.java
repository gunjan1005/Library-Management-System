package com.capgemini.controller;

import com.capgemini.authentication.AuthenticationRequest;
import com.capgemini.authentication.AuthenticationResponse;
import com.capgemini.entity.UserRole;

import com.capgemini.entity.User;
import com.capgemini.repository.RoleRepository;
import com.capgemini.repository.UserRepository;
import com.capgemini.service.JwtUtil;
import com.capgemini.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        UserRole userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        user.getRoles().add(userRole);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

@PostMapping("/authenticate")
public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
    } catch (BadCredentialsException e) {
        throw new Exception("Incorrect username or password", e);
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
}


  /*
    @PostMapping("/register")
    public ResponseEntity<String> saveLibrarian(@RequestBody User user){

        String encodePwd = pwdEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);

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
                System.out.println(jwt);
                return new ResponseEntity<>(jwt,HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return  new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }
*/





}
