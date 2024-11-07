package com.example.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String title;
    private String authour;
    private int ISBNnum;
    @Lob
    private byte [] coverImagePath;

    public Book(){}

    public Book(int ISBNnum, String title, String authour){
        this.ISBNnum = ISBNnum;
        this.title = title;
        this.authour = authour;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setISBN(int ISBNnum){
        this.ISBNnum = ISBNnum;
    }
    public int getISBN(){
        return this.ISBNnum;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    public void setAuthour(String author){
        this.authour = author;
    }

    public String getAuthour(){
        return this.authour;
    }

    public byte[] getCoverImage() {
        return this.coverImagePath;
    }

    public void setCoverImage(byte[] coverImagePath) {
        this.coverImagePath = coverImagePath;
    }
}
