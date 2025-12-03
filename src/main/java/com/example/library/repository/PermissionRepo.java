package com.example.library.repository;

import com.example.library.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission, Long> {
    Permission findByName(String name);

}