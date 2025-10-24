package com.example.library.model;

import java.util.ArrayList;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private int publication_year;
    private int quantity_of_type;
    private ArrayList<String> previous_users;
    private boolean availability;
    private ArrayList<String> genres;

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

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public int getPublication_year() { return publication_year; }
    public int getQuantity_of_type() { return quantity_of_type; }
    public ArrayList<String> getPrevious_users() { return previous_users; }
    public boolean getAvailability() { return availability; }
    public ArrayList<String> getGenres() { return genres; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setPublication_year(int publication_year) { this.publication_year = publication_year; }
    public void setQuantity_of_type(int quantity_of_type) { this.quantity_of_type = quantity_of_type; }
    public void setPrevious_users(ArrayList<String> previous_users) { this.previous_users = new ArrayList<>(previous_users); }
    public void setAvailability(boolean availability) { this.availability = availability; }
    public void setGenres(ArrayList<String> genres) { this.genres = new ArrayList<>(genres); }

}
