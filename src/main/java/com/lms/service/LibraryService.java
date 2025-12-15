package com.lms.service;

import com.lms.model.Book;
import com.lms.model.BorrowRecord;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private static LibraryService instance;
    private final List<Book> books = new ArrayList<>();
    private final List<BorrowRecord> history = new ArrayList<>();
    private int nextBookId = 1;

    private final File bookFile;
    private final File historyFile;

    // Private constructor - use getInstance(baseDir)
    private LibraryService(String baseDir) {
        File dataDir = new File(baseDir, "data");
        if (!dataDir.exists()) dataDir.mkdirs();
        this.bookFile = new File(dataDir, "books.txt");
        this.historyFile = new File(dataDir, "borrow_history.txt");
        loadBooks();
        loadHistory();
        if (books.isEmpty()) preloadBooks();
    }

    // Thread-safe singleton getter
    public static synchronized LibraryService getInstance(String baseDir) {
        if (instance == null) {
            instance = new LibraryService(baseDir);
        }
        return instance;
    }

    // ===== file IO =====
    private synchronized void saveBooks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(bookFile))) {
            for (Book b : books) {
                pw.println(b.getId() + "," + escape(b.getTitle()) + "," + escape(b.getAuthor()) + "," + b.isBorrowed());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void loadBooks() {
        books.clear();
        if (!bookFile.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(bookFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = splitCsv(line);
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = unescape(parts[1]);
                    String author = unescape(parts[2]);
                    boolean borrowed = Boolean.parseBoolean(parts[3]);
                    books.add(new Book(id, title, author, borrowed));
                    nextBookId = Math.max(nextBookId, id + 1);
                }
            }
        } catch (IOException ignored) {}
    }

    private synchronized void saveHistory() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(historyFile))) {
            for (BorrowRecord r : history) {
                pw.println(r.getBookId() + "," + escape(r.getBorrower()) + "," + r.getBorrowDate() + "," + r.getReturnDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void loadHistory() {
        history.clear();
        if (!historyFile.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(historyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = splitCsv(line);
                if (parts.length == 4) {
                    history.add(new BorrowRecord(Integer.parseInt(parts[0]), unescape(parts[1]), parts[2], parts[3]));
                }
            }
        } catch (IOException ignored) {}
    }

    // ===== utilities for simple CSV escaping =====
    private String escape(String s) {
        return s == null ? "" : s.replace("\\", "\\\\").replace(",", "\\,");
    }
    private String unescape(String s) {
        return s == null ? "" : s.replace("\\,", ",").replace("\\\\", "\\");
    }

    private String[] splitCsv(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean escape = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (escape) {
                cur.append(c);
                escape = false;
            } else {
                if (c == '\\') { escape = true; }
                else if (c == ',') { out.add(cur.toString()); cur.setLength(0); }
                else { cur.append(c); }
            }
        }
        out.add(cur.toString());
        return out.toArray(new String[0]);
    }

    // ===== core functions =====
    public synchronized List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public synchronized List<Book> getAvailableBooks() {
        return books.stream().filter(b -> !b.isBorrowed()).collect(Collectors.toList());
    }

    public synchronized Book getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public synchronized void addBook(String title, String author) {
        books.add(new Book(nextBookId++, title, author, false));
        saveBooks();
    }

    public synchronized boolean deleteBook(int id) {
        boolean removed = books.removeIf(b -> b.getId() == id);
        if (removed) saveBooks();
        return removed;
    }

    public synchronized boolean borrowBook(int id, String borrower) {
        Book b = getBookById(id);
        if (b == null || b.isBorrowed()) return false;
        b.setBorrowed(true);
        history.add(new BorrowRecord(b.getId(), borrower, new Date().toString(), "null"));
        saveBooks();
        saveHistory();
        return true;
    }

    public synchronized boolean returnBook(int id) {
        Book b = getBookById(id);
        if (b == null || !b.isBorrowed()) return false;
        b.setBorrowed(false);
        for (BorrowRecord r : history) {
            if (r.getBookId() == id && "null".equals(r.getReturnDate())) {
                r.setReturnDate(new Date().toString());
                break;
            }
        }
        saveBooks();
        saveHistory();
        return true;
    }

    public synchronized List<BorrowRecord> getHistory() {
        return new ArrayList<>(history);
    }

    private void preloadBooks() {
        String[][] defaultBooks = {
                {"The Great Gatsby", "F. Scott Fitzgerald"},
                {"To Kill a Mockingbird", "Harper Lee"},
                {"1984", "George Orwell"},
                {"Moby Dick", "Herman Melville"},
                {"Pride and Prejudice", "Jane Austen"},
                {"The Catcher in the Rye", "J.D. Salinger"},
                {"The Hobbit", "J.R.R. Tolkien"},
                {"War and Peace", "Leo Tolstoy"},
                {"Ulysses", "James Joyce"},
                {"The Odyssey", "Homer"}
        };
        for (String[] b : defaultBooks) {
            books.add(new Book(nextBookId++, b[0], b[1], false));
        }
        saveBooks();
    }
}
