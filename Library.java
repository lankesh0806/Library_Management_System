package Library_Management;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private final String FILE_NAME = "books.txt";

    public Library() {
        loadFromFile();
    }

    public void addBook(Book book) {
        books.add(book);
        saveToFile();
        System.out.println("Book added successfully.");
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void deleteBook(int id) {
        boolean removed = books.removeIf(book -> book.getId() == id);
        if (removed) {
            saveToFile();
            System.out.println("Book deleted.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void issueBook(int id) {
        for (Book book : books) {
            if (book.getId() == id && !book.isIssued()) {
                book.issueBook();
                saveToFile();
                System.out.println("Book issued successfully.");
                return;
            } else if (book.getId() == id) {
                System.out.println("Book already issued.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id && book.isIssued()) {
                book.returnBook();
                saveToFile();
                System.out.println("Book returned successfully.");
                return;
            } else if (book.getId() == id) {
                System.out.println("This book was not issued.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.println(book.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                boolean isIssued = Boolean.parseBoolean(parts[3]);
                books.add(new Book(id, title, author, isIssued));
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet – no problem
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
