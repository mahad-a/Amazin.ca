package com.example.app;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private ArrayList<Book> books = new ArrayList<>();

    private int cartSize = 0;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public int getCartSize() {
        return cartSize;
    }

    public void setCartSize(int cartSize) {
        this.cartSize = cartSize;
    }

    public void addBookToCart(Book book){
        books.add(book);
        cartSize++;
    }

    public void removeBookFromCart(Book book){
        books.remove(book);
        cartSize--;
    }
}
