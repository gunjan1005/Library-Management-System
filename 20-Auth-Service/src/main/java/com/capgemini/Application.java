package com.capgemini;

import com.capgemini.entity.UserRole;
import com.capgemini.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

//	@Autowired
//	private RoleRepository roleRepository;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		System.out.println("Librarian Auth Service Run Successfully");

	}

//	@Override
//	public void run(String... args) throws Exception {
//		// Check if roles already exist
//		if (roleRepository.findByName("ROLE_USER").isEmpty()) {
//			roleRepository.save(new UserRole("ROLE_USER"));
//		}
//		if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
//			roleRepository.save(new UserRole("ROLE_ADMIN"));
//		}
//	}

}
