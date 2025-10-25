package com.example.library;

import com.example.library.adapter.BookAdapter;
import com.example.library.app.repository.BookRepository;
import com.example.library.app.service.BookService;
import com.example.library.repository.BookRepositoryImpl;


public class App {
    public static void main(String[] args) {
        BookRepository repository = new BookRepositoryImpl();
        BookService service = new BookService(repository);
        BookAdapter adapter = new BookAdapter(service);

        // поскольку адаптер - точка входа, сделал старт программы там
        adapter.startProgram();
    }
}

