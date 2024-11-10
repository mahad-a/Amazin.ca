package com.example.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInventory extends CrudRepository<Book, Long> {
    List<Book> findByTitleContainingOrAuthorContainingOrISBNnumContaining(String title, String author, String isbn);
}
