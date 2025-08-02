package Library_Management;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public Book(int id, String title, String author, boolean isIssued) {
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issueBook() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    @Override
    public String toString() {
        return "Book ID: " + id + " | Title: " + title + " | Author: " + author +
                " | Status: " + (isIssued ? "Issued" : "Available");
    }
    public String toFileString() {
        return id + "," + title + "," + author + "," + isIssued;
    }
}
