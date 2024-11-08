package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    void testSetAndGetId() {
        book.setId(1L);
        assertEquals(1L, book.getId());
    }

    @Test
    void testSetAndGetISBN() {
        book.setISBN(123456789);
        assertEquals(123456789, book.getISBN());
    }

    @Test
    void testSetAndGetTitle() {
        book.setTitle("Great Gatsby");
        assertEquals("Great Gatsby", book.getTitle());
    }

    @Test
    void testSetAndGetAuthor() {
        book.setAuthor("FirasSpear");
        assertEquals("FirasSpear", book.getAuthor());
    }

    @Test
    void testSetAndGetCoverImage() {
        byte[] image = {0, 1, 2, 3, 4};
        book.setCoverImage(image);
        assertArrayEquals(image, book.getCoverImage());
    }

    @Test
    void testConstructorWithParameters() {
        Book bookWithParams = new Book(123456789, "The Great Gatsby", "Firas");
        assertEquals(123456789, bookWithParams.getISBN());
        assertEquals("The Great Gatsby", bookWithParams.getTitle());
        assertEquals("Firas", bookWithParams.getAuthor());
    }

    @Test
    void testDefaultIdIsNull() {
        assertNull(book.getId());
    }

    @Test
    void testDefaultTitleIsNull() {
        assertNull(book.getTitle());
    }

    @Test
    void testDefaultAuthorIsNull() {
        assertNull(book.getAuthor());
    }

    @Test
    void testDefaultCoverImageIsNull() {
        assertNull(book.getCoverImage());
    }
}
