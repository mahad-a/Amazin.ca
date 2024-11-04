package com.example.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInventory extends CrudRepository<Book, Long> {
    
}
