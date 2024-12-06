package com.example.app;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookInventory extends CrudRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(b.author) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Book> findByTitleContainingOrAuthorContainingIgnoreCase(@Param("query") String query);

    @Query("SELECT b FROM Book b WHERE CAST(b.ISBNnum AS string) LIKE CONCAT('%', :isbn, '%')")
    List<Book> findByISBNnumContaining(@Param("isbn") String isbn);
    List<Book> findBooksByAuthorIn(Set<String> authors);
}
