package com.lms.model;

public class BorrowRecord {
    private final int bookId;
    private final String borrower;
    private final String borrowDate;
    private String returnDate;

    public BorrowRecord(int bookId, String borrower, String borrowDate, String returnDate) {
        this.bookId = bookId;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getBookId() { return bookId; }
    public String getBorrower() { return borrower; }
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }

    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "BookID: " + bookId + " | Borrower: " + borrower + " | Borrowed on: "
                + borrowDate + " | Returned on: " + (returnDate.equals("null") ? "Not Returned" : returnDate);
    }
}
