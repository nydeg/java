package com.example.library.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private int publication_year;
    private int quantity_of_type;
    private ArrayList<String> previous_users;
    private boolean availability;
    private ArrayList<String> genres;
    static int number = 0;

    public Book() {
        this.previous_users = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public Book(String title, String author, String publisher, int publication_year, int quantity_of_type,
                ArrayList<String> previous_users, ArrayList<String> genres) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publication_year = publication_year;
        this.quantity_of_type = quantity_of_type;
        this.previous_users = previous_users;
        this.availability = true;
        this.genres = genres;
    }

    public Book(String title, String author) {
        this.quantity_of_type = 1;
        this.title = title;
        this.author = author;
        this.publisher = "";
        this.publication_year = 0;
        this.previous_users = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public int getPublication_year() { return publication_year; }
    public int getQuantity_of_type() { return quantity_of_type; }
    public ArrayList<String> getPrevious_users() { return previous_users; }
    public boolean getAvailability() { return availability; }
    public ArrayList<String> getGenres() { return genres; }
    public static int getNumber() {return number; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setPublication_year(int publication_year) { this.publication_year = publication_year; }
    public void setQuantity_of_type(int quantity_of_type) { this.quantity_of_type = quantity_of_type; }
    public void setPrevious_users(ArrayList<String> previous_users) { this.previous_users = new ArrayList<>(previous_users); }
    public void setAvailability(boolean availability) { this.availability = availability; }
    public void setGenres(List<String> genres) { this.genres = new ArrayList<>(genres); }
}


