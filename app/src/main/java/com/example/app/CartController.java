package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class CartController {
    @Autowired
    private BookInventory bookInventory;
    @Autowired
    private Cart cart;
    @Autowired
    private CartRepository cartRepository;

    public CartController(){
        // empty constructor for now
    }

    @GetMapping("/cart")
    public String displayCart() {
        return "cart";
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam Long bookID){
        // find if book exists in inventory
        Optional<Book> book = bookInventory.findById(bookID);

        // check if book is present
        if (book.isPresent()) {
            // add to the cart
            cart.addBookToCart(book.get());
            cartRepository.save(cart);

            System.out.println(book.get().getISBN());
            System.out.println(book.get().getAuthour());
            System.out.println(book.get().getTitle());

            return ResponseEntity.ok("Book added to cart successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Book not found.");
        }

    }


}
