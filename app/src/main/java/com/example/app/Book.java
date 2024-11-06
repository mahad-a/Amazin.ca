package com.example.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {

    int ISBN;
    String title;
    String authour;
    byte [] coverURL;

    @Id
    Long id;
    
    Book(int ISBN, String title, String authour, byte [] coverURL){
        this.ISBN = ISBN;
        this.title = title;
        this.authour = authour;
        this.coverURL = coverURL;
    }

    Book(){
        
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setISBN(int ISBN){
        this.ISBN = ISBN;

    }
    public int getISBN(int ISBN){
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

    public void setCover(byte [] coverURL){
        this.coverURL = coverURL;
    }

    public byte [] getCover(){
        return this.coverURL;
    }
    
}
