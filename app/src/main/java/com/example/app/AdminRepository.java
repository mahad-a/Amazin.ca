package com.example.app;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long>{
    Admin findByUsername(String username);
}
