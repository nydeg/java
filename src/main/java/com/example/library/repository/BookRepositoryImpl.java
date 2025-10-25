package com.example.library.repository;

import com.example.library.app.repository.BookRepository;
import com.example.library.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepositoryImpl implements BookRepository {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) { // метод для сохранения книги со всеми атрибутами
        books.add(book);
        System.out.printf("Книга <%s> с полными атрибутами успешно сохранена\n", book.getTitle());
    }


    @Override
    public List<Book> findByAttributes(
            String title,
            String author,
            String publisher,
            Integer publicationYear,
            List<String> genres) {

        return books.stream()
                .filter(book -> {
                    if (title != null && !title.isEmpty()) { // фильтр по названию
                        if (book.getTitle() == null ||
                                !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                            return false;
                        }
                    }

                    // по автору
                    if (author != null && !author.isEmpty()) {
                        if (book.getAuthor() == null ||
                            !book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                            return false;
                        }
                    }

                    // по издателю
                    if (publisher != null && !publisher.isEmpty()) {
                        if (book.getPublisher() == null ||
                                !book.getPublisher().toLowerCase().contains(publisher.toLowerCase())) {
                            return false;
                        }
                    }

                    // по году

                    if (genres != null && !genres.isEmpty()) {
                        if (book.getGenres() == null || book.getGenres().isEmpty()) {
                            return false;
                        }

                        boolean hasMatchingGenre = genres.stream()
                                .anyMatch(genre ->
                                        book.getGenres().stream()
                                                .anyMatch(g -> g.equalsIgnoreCase(genre.trim())));
                        if (!hasMatchingGenre) {
                            return false;
                        }

                    }

                    return true;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String title) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle() != null && book.getTitle().equalsIgnoreCase(title)) {
                books.remove(i);
                System.out.printf("Книга <%s> успешно удалена\n", title);
                return; // удаляем только одну
            }
        }
        System.out.println("Не нашлось книги с таким названием");
    }

//    @Override
//    public Book findByName(String name) {
//        return ;
//    }

    @Override
    public List<Book> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(books));
    }
}
