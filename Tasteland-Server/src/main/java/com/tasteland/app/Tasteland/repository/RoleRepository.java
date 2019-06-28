package com.tasteland.app.Tasteland.repository;

import com.tasteland.app.Tasteland.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String role);
}
