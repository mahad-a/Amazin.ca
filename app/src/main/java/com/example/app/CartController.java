package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
                System.out.println("Content: " + user.getCart().getBooks());
                System.out.println("From: " + user.getUsername());
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
    public ResponseEntity<String> addToCart(@RequestParam Long bookID, @RequestParam String username) {
        // find if book exists in inventory
        Iterable<Book> books = bookInventory.findAll();

        Book foundBook;

        for(Book book : books){
            if (book.id == bookID){
                foundBook = book;
                Iterable<User> users = userRepository.findAll();

                for (User user : users){

                    if (user.getUsername().equals(username)){

                        int count = 0;
                        for (Book book_cart : user.getCart().getBooks()){

                            if (book_cart.title.equals(foundBook.getTitle())){
                                count++;
                            }
                        }

                        if (count < foundBook.getQuantity()){
                            user.getCart().addBookToCart(foundBook);
                            System.out.println("Added:" + book.getTitle() + " To: " + user.getUsername() + " Cart Contents: " + user.getCart().getBooks().toString());
                            userRepository.save(user);
                            return ResponseEntity.ok("Book added to cart successfully.");
                        }
                        else{
                            return ResponseEntity.ok("Cannot add another book.");
                        }

                    }

                }
            }

        }
        return ResponseEntity.ok("Could not find book");
    }

    /**
     * Removes book from the shopping cart
     * @param bookID
     * @return ResponseEntity
     */
    @DeleteMapping("/removeFromCart")
    public ResponseEntity<Double> removeFromCart(@RequestParam Long bookID, @RequestParam String username) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                Cart userCart = user.getCart();
                Optional<Book> bookToRemove = userCart.getBooks().stream()
                        .filter(book -> book.getId().equals(bookID))
                        .findFirst();

                if (bookToRemove.isPresent()) {
                    userCart.removeBookFromCart(bookToRemove.get());
                    cartRepository.save(userCart);
                    return ResponseEntity.ok(bookToRemove.get().getPrice());
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Checkout a book from the cart
     * @param bookID The ID of the book to checkout
     * @param username The username of the user checking out the book
     * @return ResponseEntity
     */
    @PostMapping("/checkoutBook")
    public ResponseEntity<String> checkoutBook(@RequestParam Long bookID, @RequestParam String username) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                Cart userCart = user.getCart();
                Optional<Book> bookToCheckout = userCart.getBooks().stream()
                        .filter(book -> book.getId().equals(bookID))
                        .findFirst();

                if (bookToCheckout.isPresent()) {
                    Book book = bookToCheckout.get();

                    if (book.getQuantity() <= 0) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Book is out of stock.");
                    }

                    book.setQuantity(book.getQuantity() - 1);
                    bookInventory.save(book);

                    userCart.removeBookFromCart(book);
                    cartRepository.save(userCart);

                    return ResponseEntity.ok("Book checked out successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Book not found in the cart.");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: User not found.");
    }
}
