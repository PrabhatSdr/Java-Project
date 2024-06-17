package com.example.javaprojectlib;

import java.util.ArrayList;
import java.util.List;

public class LibraryServer {
    private List<Book> books;
    private int nextId;

    public LibraryServer() {
        books = new ArrayList<>();
        nextId = 1;
    }

    public List<Book> listBooks() {
        return books;
    }

    public void addBook(String title, String author) {
        Book book = new Book(nextId++, title, author, true);
        books.add(book);
    }

    public static void main(String[] args) {
        LibraryServer server = new LibraryServer();
        // Sample books for testing
        server.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        server.addBook("1984", "George Orwell");

        // Listing books with availability status
        List<Book> books = server.listBooks();
        for (Book book : books) {
            String availability = book.isAvailable() ? "Available" : "Not Available";
            System.out.println(book.getId() + ": " + book.getTitle() + " by " + book.getAuthor() + " (" + availability + ")");
        }

    }
}

