package com.example.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public ResponseEntity<Iterable<Book>> getAllBooks() {

        Iterable<Book> books = bookInventory.findAll();

        return ResponseEntity.ok(books);

    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(
            @RequestParam Long ISBN,
            @RequestParam String title,
            @RequestParam String authour,
            @RequestParam("coverImage") MultipartFile coverImage // New parameter
    ) {
        try {
            // First, handle the image file
            String fileName = coverImage.getOriginalFilename();
            // You might want to generate a unique filename to avoid conflicts
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

            // Save the file to a directory (you'll need to create this directory)
            String uploadDir = "uploads/covers/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(coverImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Create and save the book with the image path
            Book book = new Book(ISBN, title, authour);
            book.setCoverImagePath(uploadDir + uniqueFileName); // You'll need to add this field to your Book class

            Book savedBook = bookInventory.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);

        } catch (IOException e) {
            // Handle file upload error
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

}
