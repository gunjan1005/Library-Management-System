package com.capgemini.repository;

import com.capgemini.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);
}
