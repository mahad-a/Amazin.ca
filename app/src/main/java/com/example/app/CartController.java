package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private BookInventory bookInventory;
    @Autowired
    private Cart cart;
    @Autowired
    private CartRepository cartRepository;
    @Autowired 
    private UserRepository userRepository;
    

    public CartController(){
        // empty constructor for now
    }

    /**
     * Displays the current cart
     * @return cart.html
     */
    @GetMapping("/displayCart")
    public String displayCart() {
        return "cart";
    }

    /**
     * Gets the books within the cart and displays to the html
     * @return ResponseEntity
     */
    @GetMapping("/getCart")
    public ResponseEntity<Cart> getCart(@RequestParam String username){
        Iterable<User> users = userRepository.findAll();

        for(User user : users){
            if (user.getUsername().equals(username)){
                System.out.println("retrieved Cart" + user.getCart());
                return ResponseEntity.ok(user.getCart());
            }
        }
        System.out.println("User not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        
    }

    /**
     * Adds a book to the shopping cart
     * @param bookID
     * @return RepsonseEntity
     */
    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam Long bookID, @RequestParam String username){
        // find if book exists in inventory
        Iterable<Book> books = bookInventory.findAll();

        Book foundBook;

        for(Book book : books){
            if (book.id == bookID){
                foundBook = book;
                Iterable<User> users = userRepository.findAll();

                for (User user : users){
                    if (user.getUsername().equals(username)){
                        user.getCart().addBookToCart(foundBook);
                        return ResponseEntity.ok("Book added to cart successfully.");
                    }
                } 
            }
        }
        
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Book not found.");
        

    }

    /**
     * Removes book from the shopping cart
     * @param bookID
     * @return RepsonseEntity
     */
    @DeleteMapping("/removeFromCart")
    public ResponseEntity<String> removeFromCart(@RequestParam Long bookID) {
            Optional<Book> bookToRemove = bookInventory.findById(bookID);
            if (bookToRemove.isPresent()) {
                for (Book book : cart.getBooks()) {
                    if (book.getId().equals(bookID)) {
                        cart.removeBookFromCart(book);
                        cartRepository.save(cart);
                        return ResponseEntity.ok("Book removed from cart successfully.");
                    }
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Book not found in the cart.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Book not found in the inventory.");
            }
    }


}
