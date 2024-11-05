package com.example.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookInventory bookInventory;

    BookController(){

    }

    @GetMapping("/adminAddBookPage")
    public String addBookHTML() {
        return "adminAddBookPage";
    }
    
    

    @GetMapping("/get")
    public ResponseEntity<Optional<Book>> getMethodName(@RequestParam Long id) {
        Optional<Book> retreivedBooks = bookInventory.findById(id);
        return ResponseEntity.ok(retreivedBooks);
    }

    @PostMapping(value = "/add", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Book> addBook(@RequestParam("title") String title,
                        @RequestParam("author") String author,
                        @RequestParam("image") MultipartFile image) throws Exception {

        Book book = new Book();
        book.setTitle(title);
        book.setAuthour(author);
        book.setCover(image.getBytes());  
        Book savedBook = bookInventory.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
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
