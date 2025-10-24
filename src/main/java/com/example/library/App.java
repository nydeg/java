package com.example.library;

import com.example.library.model.Book;

public class App {
    public static void main(String[] args) {
        System.out.println("Oh");

        Book book = new Book("Мастер и маргарита");
        String title = book.getTitle();
        System.out.println(title);
    }
}

