package com.example.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {

    int ISBN;
    String title;
    String authour;

    @Id
    Long id;
    
    Book(int ISBN, String title, String authour){
        this.ISBN = ISBN;
        this.title = title;
        this.authour = authour;
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

    public void setAuthour(String authour){
        this.authour = authour;
    }

    public String getAuthour(){
        return this.authour;
    }
    
}
