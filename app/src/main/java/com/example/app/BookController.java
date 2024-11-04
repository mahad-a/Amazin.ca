package com.example.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookInventory bookInventory;

    BookController(){

    }
    

    @GetMapping("/get")
    public ResponseEntity<Optional<Book>> getMethodName(@RequestParam Long id) {
        Optional<Book> retreivedBooks = bookInventory.findById(id);
        return ResponseEntity.ok(retreivedBooks);
    }

    @PostMapping("/put")
    public ResponseEntity<String> addBook(@RequestBody int ISBN, String title, String authour) {
        Book book = new Book(ISBN, title, authour);
        bookInventory.save(book);
        
        return ResponseEntity.ok("Book successfully added.");
    }

    @DeleteMapping("/del")
    public ResponseEntity<String> deleteBook(@RequestParam Long id){
        Optional<Book> books = bookInventory.findById(id);

        if(books.isPresent()){
            bookInventory.delete(books.get());
            return ResponseEntity.ok("The book has been deleted.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
    }
    
    
    
}
