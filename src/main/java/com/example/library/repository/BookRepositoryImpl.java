package com.example.library.repository;

import com.example.library.app.repository.BookRepository;
import com.example.library.model.Book;

import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class BookRepositoryImpl implements BookRepository {
    private final List<Book> books = new ArrayList<>();
    private static final String FILE_NAME = "src/main/java/com/example/library/util/library_books.txt";

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

    @Override
    public Book findByTitleAndAuthor(String title, String author) {
        return books.stream()
                .filter(book -> {
                    boolean matchesTitle = true;
                    boolean matchesAuthor = true;

                    // title по точному совпадению независимо от регистра
                    if (title != null && !title.isEmpty()) {
                        matchesTitle = book.getTitle() != null &&
                                book.getTitle().equalsIgnoreCase(title.trim());
                    }

                    if (author != null && !title.isEmpty()) {
                        matchesAuthor = book.getAuthor() != null &&
                            book.getAuthor().equalsIgnoreCase(author.trim());
                    }

                    return matchesTitle || matchesAuthor;
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(books));
    }

    @Override
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                String genreStr = String.join(",", book.getGenres());
                writer.write(String.format("%s|%s|%s|%d|%s",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublisher(),
                        book.getPublication_year(),
                        genreStr
                ));
                writer.newLine();
            }
            System.out.printf("Книги сохранены в %s\n", FILE_NAME);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    @Override
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Файл не найден. Библиотека останется пустой.");
            return;
        }

        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1); // -1 сохраняет пустые поля
                if (parts.length == 5) {
                    Book book = new Book();
                    book.setTitle(parts[0]);
                    book.setAuthor(parts[1]);
                    book.setPublisher(parts[2]);
                    book.setPublication_year(Integer.parseInt(parts[3]));

                    List<String> genres = new ArrayList<>();
                    if (!parts[4].isEmpty()) {
                        String[] genreArray = parts[4].split(",");
                        for (String genre : genreArray) {
                            genres.add(genre.trim());
                        }
                    }
                    book.setGenres(genres);

                    books.add(book);
                }
            }
            System.out.println("Загружено " + books.size() + " книг");
        }
        catch (IOException e) {
            System.err.println("Ошибка при загрузке: " + e.getMessage());
        }
    }
}
