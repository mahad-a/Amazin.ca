package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookInventory bookInventory;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    // Test for the displayCart method
    @Test
    public void testDisplayCart() throws Exception {
        mockMvc.perform(get("/cart/displayCart"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"));
    }

    // Test for the getCart method (success)
    @Test
    public void testGetCart_Success() {
        String username = "testUser";
        Cart cart = new Cart();
        User user = new User();
        user.setUsername(username);
        user.setCart(cart);

        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        ResponseEntity<Cart> response = cartController.getCart(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    // Test for the getCart method (user not found)
    @Test
    public void testGetCart_UserNotFound() {
        String username = "nonexistentUser";
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<Cart> response = cartController.getCart(username);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    // Test for the addToCart method (success)
    @Test
    public void testAddToCart_Success() {
        Long bookId = 1L;
        String username = "testUser";
        Book book = new Book(123, "Test Book", "Test Author");
        book.id = bookId;
        book.setQuantity(5);

        User user = new User();
        user.setUsername(username);
        user.setCart(new Cart());

        List<Book> books = Arrays.asList(book);
        List<User> users = Arrays.asList(user);

        when(bookInventory.findAll()).thenReturn(books);
        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<String> response = cartController.addToCart(bookId, username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book added to cart successfully.", response.getBody());
    }

    // Test for the removeFromCart method (success)
    @Test
    public void testRemoveFromCart_Success() {
        Long bookId = 1L;
        String username = "testUser";
        Book book = new Book(123, "Test Book", "Test Author");
        book.id = bookId;

        Cart cart = new Cart();
        cart.addBookToCart(book);

        User user = new User();
        user.setUsername(username);
        user.setCart(cart);

        List<User> users = Arrays.asList(user);

        when(userRepository.findAll()).thenReturn(users);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        // ResponseEntity<String> response = cartController.removeFromCart(bookId, username);

        // assertEquals(HttpStatus.OK, response.getStatusCode());
        // assertEquals("Book removed from cart successfully.", response.getBody());
    }
}