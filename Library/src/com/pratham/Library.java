package com.pratham;

import java.util.ArrayList;
import java.util.Date;

public class Library {

    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int getBookCount() {
        int count = 0;
        for ( Book book: books) {
            count++;
        }
        return count;
    }

    public Book addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
        return book;
    }

    public ArrayList<Book> findBooksByTitle(String title) {
        ArrayList<Book> booksFound = new ArrayList<Book>();
        for ( Book book: books ) {
            if ( book.getTitle().toLowerCase().contains(title.toLowerCase()) ) {
                booksFound.add(book);
            }
        }
        return booksFound;
    }

    public ArrayList<Book> findBooksByAuthor(String author) {
        ArrayList<Book> booksFound = new ArrayList<Book>();
        for ( Book book: books ) {
            if ( book.getAuthorName().toLowerCase().contains(author.toLowerCase()) ) {
                booksFound.add(book);
            }
        }
        return booksFound;
    }

    public ArrayList<Book> findBorrowedBooks() {
        ArrayList<Book> booksFound = new ArrayList<Book>();
        for ( Book book: books ) {
            if ( book.isBorrowed() ) {
                booksFound.add(book);
            }
        }
        return booksFound;
    }

    public ArrayList<Book> findOverdueBooks() {
        ArrayList<Book> booksFound = new ArrayList<Book>();
        for ( Book book: books ) {
            if ( book.isBorrowed() ) {
                Date today = new Date();
                Date bDate = book.getBorrowDate();
                long days = Math.round( (today.getTime() - bDate.getTime()) / (double) 86400000);
                if ( days > 14 ) {
                    booksFound.add(book);
                }
            }
        }
        return booksFound;
    }
}
