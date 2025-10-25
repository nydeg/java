package com.example.library.adapter;

import com.example.library.app.service.BookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// класс для работы с консолью
public class BookAdapter {
    private final BookService service;
    private Scanner scanner = new Scanner(System.in);

    public BookAdapter(BookService service) {
        this.service = service;
    }

    public void startProgram() {
        System.out.println("Перед вами консольное приложение Библиотека. \n" +
                        "Чтобы выбрать действие, нажмите на соответствующую цифру");

        // тут цикл вайл и запуск методов из адаптера
        int number = 0;
        boolean running = true;
        menu();

        while (running) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("введено не число, попробуйте еще раз");
                continue;
            }

            switch (number) {
                case 0: running = false; break;
                case 1: addBook(); break;
                case 2: addBookFully(); break;
                case 3: removeBook(); break;
                case 4: getAllBooks(); break;
                case 5: findBookByTitleAndAuthor(); break;
                case 6: saveToFile(); break;
                case 7: loadFromFile(); break;
                case 8: findByAttributes(); break;
                default: System.out.println("введено неправильное число, попробуйте еще раз");
            }
            menu();
        }
    }

    private void menu() {
        System.out.println(
                "1: добавить книгу по названию и автору\n" +
                "2: добавить книгу со всеми атрибутами\n" +
                "3: удалить книгу\n" +
                "4: вывести список книг\n" +
                "5: найти книгу по названию и автору\n" +
                "6: сохранить в файл\n" +
                "7: загрузить из файла\n" +
                "8: поиск книги по атрибутам\n" +
                "0: остановить программу");
    }

    private void addBook() {
        String title = "smth";
        String author = "smth";

        System.out.println("Введите название книги: ");
        if (scanner.hasNextLine()) {
            title = scanner.nextLine();
        }

        System.out.println("Введите автора данной книги: ");
        if (scanner.hasNextLine()) {
            author = scanner.nextLine();
            boolean isValid = author.matches("[\\p{L}\\s\\-'\\.]+"); // валидация, хотя бы какая-то
            if (!isValid) {
                System.out.println("Имя автора содержит недопустимые символы, попробуйте еще раз");
                return;
            }
        }

        service.saveBook(title, author);
    }

    private void addBookFully() {
        String title = "smth";
        String author = "smth";
        String publisher = "smth";
        int year = 0;
        int quantity = 1;
        ArrayList<String> previous_users = new ArrayList<>();
        ArrayList<String> genres = new ArrayList<>();


        System.out.println("Введите название книги: ");
        if (scanner.hasNextLine()) {
            title = scanner.nextLine().trim();
        }

        System.out.println("Введите автора данной книги: ");
        if (scanner.hasNextLine()) {
            author = scanner.nextLine().trim();
            boolean isValid = author.matches("[\\p{L}\\s\\-'\\.]+"); // валидация, хотя бы какая-то
            if (!isValid) {
                System.out.println("Имя автора содержит недопустимые символы, попробуйте еще раз");
                return;
            }
        }

        System.out.println("Введите издателя: ");
        if (scanner.hasNextLine()) {
            publisher = scanner.nextLine().trim();
            boolean isValid = publisher.matches("[\\p{L}\\s\\-'\\.]+");
            if (!isValid) {
                System.out.println("Имя издателя содержит недопустимые символы, попробуйте еще раз");
                return;
            }
        }

        System.out.println("Введите год публикации: ");
        if (scanner.hasNextInt()) {
            if (year < 0 && year > 2025) {
                System.out.println("Несоответствующий год, повторите попытку");
                return;
            }
            year = scanner.nextInt();
        } else {
            System.out.println("Требуется ввести число, повторите попытку");
            return;
        }
        scanner.nextLine();

        System.out.println("Введите жанры через пробел: ");
        String genreInput = scanner.nextLine().trim();

        if (!genreInput.isEmpty()) {
            String[] parts = genreInput.split("\\s+"); // \\s+ = один или более пробелов
            for (String part : parts) {
                if (!part.isEmpty()) {
                    genres.add(part);
                }
            }
        }

        service.saveBook(title, author, publisher, year, quantity, previous_users, genres);
    }


    private void removeBook() {
        System.out.println("Введите название книги, котрую вы хотите удалить: ");
        String title = "smth";

        title = scanner.nextLine();
        service.removeBook(title);
    }

    private void getAllBooks() {
        service.getBooks();
    }

    private void findBookByTitleAndAuthor() {
        String title = "smth";
        String author = "smth";

        System.out.println("Введите название книги для поиска: ");
        title = scanner.nextLine();

        System.out.println("Введите автора: ");
        author = scanner.nextLine();

        String answer = service.findBookByTitleAndAuthor(title, author);
        System.out.println(answer);
    }

    private void saveToFile() {
        service.saveToFile();
    }

    private void loadFromFile() {
        service.loadFromFile();
    }

    private void findByAttributes() {
        System.out.println("Поиск книги по атрибутам (оставьте поле пустым, чтобы пропустить):");

        System.out.print("Название: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) title = null;

        System.out.print("Автор: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) author = null;

        System.out.print("Издатель: ");
        String publisher = scanner.nextLine().trim();
        if (publisher.isEmpty()) publisher = null;

        System.out.print("Год издания: ");
        String yearStr = scanner.nextLine().trim();
        Integer year = null;
        if (!yearStr.isEmpty()) {
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат года — фильтр по году отключён.");
            }
        }

        System.out.print("Жанры через пробел: ");
        String genreInput = scanner.nextLine().trim();
        List<String> genres = null;
        if (!genreInput.isEmpty()) {
            genres = Arrays.asList(genreInput.split("\\s+"));
        }

        String answer = service.findBooksByAttributes(title, author, publisher, year, genres);
        System.out.println(answer);
    }
}


