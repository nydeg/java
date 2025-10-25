package com.example.library.app.service;

import com.example.library.app.repository.BookRepository; // импортируме только интерфей, реализация в другом слое
import com.example.library.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void saveBook(String title, String author) {
        repository.save(new Book(title, author));
    }

    public void saveBook(String title, String author, String publisher, int publication_year, int quantity_of_type,
                         ArrayList<String> previous_users, ArrayList<String> genres) {
        repository.save(new Book(title, author, publisher, publication_year, quantity_of_type, previous_users, genres));
    }

    public void removeBook(String title) {
        repository.delete(title);
    }

    public void getBooks() {
        if (repository.getAll().size() > 0) {
            for (Book book : repository.getAll()) {
                System.out.println(book.getTitle());
            }
        } else {
            System.out.println("На данный момент книг нет");
        }
    }

    public String findBookByTitleAndAuthor(String title, String author) {
        Book book = repository.findByTitleAndAuthor(title, author);
        if (book == null) {
            return "Книга не найдена";
        }
        return "Найдена книга: \n" +
                "Название: " + book.getTitle() + "\n" +
                "Автор: " + book.getAuthor();
    }

    public String findBooksByAttributes(String title,
                                        String author,
                                        String publisher,
                                        Integer publicationYear,
                                        List<String> genres) {

        // чето потерял архитектуру, у сервиаса не должно быть доступа к моделям
        // но конкретно в этом проекте думаю не критично
        List<Book> results = repository.findByAttributes(title, author, publisher, publicationYear, genres);

        if (results.isEmpty()) {
            return "Книги по заданным критериям не найдены.";
        }

        StringBuilder response = new StringBuilder();
        response.append("Совпадений найдено: ").append(results.size()).append("\n");

        for (Book book : results) {
            response.append("- Название: ").append(book.getTitle())
                    .append("\n- Автор: ").append(book.getAuthor())
                    .append("\n- Издатель: ").append(book.getPublisher())
                    .append("\n- Год: ").append(book.getPublication_year())
                    .append("\n- Жанры").append(book.getGenres() == null ? "-" : book.getGenres())
                    .append("\n\n");
        }

        return response.toString().trim();
    }

    public void saveToFile() {
        repository.saveToFile();
    }

    public void loadFromFile() {
        repository.loadFromFile();
    }
}




