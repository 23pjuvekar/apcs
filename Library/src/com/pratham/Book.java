package com.pratham;

import java.util.Date;

public class Book {

    private String title;
    private String authorName;
    private String borrowerName;
    private Date borrowDate;

    public Book(String title, String authorName) {
        this.title = title;
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public boolean isBorrowed() {
        if ( borrowerName == null ||  borrowDate == null ) {
            return false;
        }
        return true;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void checkOut(String borrowerName, Date borrowDate) {
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
    }

    public void checkIn() {
        this.borrowerName = null;
        this.borrowDate = null;
    }

}
