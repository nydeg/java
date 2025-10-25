package com.example.library.app.repository;


import com.example.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository {
    final List<Book> books = new ArrayList<>();

    List<Book> findByAttributes(
            String title,
            String author,
            String publisher,
            Integer publicationYear,
            List<String> genres
    );

    void save(Book book);
    void delete(String title);

    // Book findByName(String name);
    List<Book> getAll();
}
