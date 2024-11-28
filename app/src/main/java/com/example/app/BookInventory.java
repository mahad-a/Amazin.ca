package com.example.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookInventory extends CrudRepository<Book, Long> {
    List<Book> findByTitleContainingOrAuthorContainingIgnoreCase(String title, String author);
    List<Book> findByISBNnum(int isbn);
    List<Book> findBooksByAuthorIn(Set<String> authors);
}
