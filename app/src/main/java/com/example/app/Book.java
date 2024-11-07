package com.example.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

  
    String title;
    String authour;
    String coverImagePath;

    public String getCoverImagePath() {
        return this.coverImagePath;
    }

    public void setCoverImagePath(String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ISBN;
    
    Book(Long ISBN, String title, String authour){
        this.ISBN = ISBN;
        this.title = title;
        this.authour = authour;
      
    }

    Book(){
        
    }

    public void setISBN(Long ISBN){
        this.ISBN = ISBN;

    }
    public Long getISBN(int ISBN){
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
