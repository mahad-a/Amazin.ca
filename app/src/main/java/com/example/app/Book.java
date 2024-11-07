package com.example.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Book {

  
    String title;
    String authour;
    int ISBN;
    @Lob
    byte [] coverImagePath;

    public byte[] getCoverImage() {
        return this.coverImagePath;
    }

    public void setCoverImage(byte[] coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    Book(int ISBN, String title, String authour){
        this.ISBN = ISBN;
        this.title = title;
        this.authour = authour;
      
    }

    Book(){
        
    }

    public void setISBN(int ISBN){
        this.ISBN = ISBN;

    }
    public int getISBN(){
        return this.ISBN;
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
    
}
