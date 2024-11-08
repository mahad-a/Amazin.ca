package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    public CartController(){
        // empty constructor for now
    }

    @GetMapping("/displayCart")
    public String displayCart() {
        return "cart";
    }

    @GetMapping("/getCart")
    public ResponseEntity<List<Cart>> getCart(){
        try{
            List<Cart> carts = StreamSupport.stream(cartRepository.findAll().spliterator(), false).toList();
            for (Cart cart: carts){
                for (Book book: cart.getBooks()){
                    System.out.println("-------Currently in the cart-------");
                    System.out.println(book.getISBN());
                    System.out.println(book.getAuthor());
                    System.out.println(book.getTitle());
                    System.out.println(book.getCoverImage());
                    System.out.println("Book id = " + book.getId());
                }
            }
            return ResponseEntity.ok(carts);
        } catch (Exception e) {
            System.out.println("failed to retrieve the shopping cart.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
            System.out.println(book.get().getAuthor());
            System.out.println(book.get().getTitle());

            return ResponseEntity.ok("Book added to cart successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Book not found.");
        }

    }


}
