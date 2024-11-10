package com.example.app;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.multipart.MultipartFile;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookInventory bookInventory;

    BookController() {

    }

    @GetMapping("/adminAddBookPage")
    public String addBookHTML() {
        return "adminAddBookPage";
    }

    @GetMapping("/get")
    public ResponseEntity<Optional<Book>> getBook(@RequestParam Long id) {
        Optional<Book> retreivedBooks = bookInventory.findById(id);
        return ResponseEntity.ok(retreivedBooks);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks() {

        try{
            List<Book> books = StreamSupport.stream(bookInventory.findAll().spliterator(), false).collect(Collectors.toList());

            for (Book book: books){
                System.out.println(book.getISBN());
                System.out.println(book.getAuthor());
                System.out.println(book.getTitle());
                System.out.println(book.getCoverImage());
            }
                                
            return ResponseEntity.ok(books);
        }catch (Exception e){
            System.out.println("failed to retrieve books.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(
        @RequestParam int ISBNnum,
        @RequestParam String title,
        @RequestParam String author,
        @RequestParam("coverImage") MultipartFile coverImage
    ) {
        try {
            System.out.println("isbn number added: " + ISBNnum);
            Book book = new Book(ISBNnum, title, author);
            book.setCoverImage(coverImage.getBytes()); // Save the image data
            Book savedBook = bookInventory.save(book);
            System.out.println("Cover image size: " + coverImage.getSize() + " bytes");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/del")
    public ResponseEntity<String> deleteBook(@RequestParam Long id) {
        Optional<Book> books = bookInventory.findById(id);

        if (books.isPresent()) {
            bookInventory.delete(books.get());
            return ResponseEntity.ok("The book has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
    }


    @PostMapping("/update")
    public ResponseEntity<String> postMethodName(
        @RequestParam("id") Long id,
        @RequestParam("isbn") int isbn,
        @RequestParam("title") String title,
        @RequestParam("author") String author,
        @RequestParam(value = "coverImage", required = false) MultipartFile coverImage
    ) {
        Optional<Book> books = bookInventory.findById(id);
        if (books.isPresent()){
            Book book = books.get();
            book.setISBN(isbn);
            book.setAuthor(author);
            book.setTitle(title);
            try {
                book.setCoverImage(coverImage.getBytes());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            bookInventory.save(book);

            return ResponseEntity.ok("Book Updated");

            

        }
        else{
            return ResponseEntity.ok("No Book Found");
        }
    }

    //
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> validBooks;
        try {
            
            Integer isbnnum = Integer.parseInt(query);
            validBooks = bookInventory.findByISBNnum(isbnnum);
        } catch (NumberFormatException e) {
           
            validBooks = bookInventory.findByTitleContainingOrAuthorContaining(query, query);
        }
        return ResponseEntity.ok(validBooks);
    }
}
