package com.library.library_management_system.exception;

import lombok.Getter;

@Getter
public class BookNotAvailableException extends RuntimeException {
    private final String bookId;
    private final String bookTitle;

    public BookNotAvailableException(String message) {
        super(message);
        this.bookId = null;
        this.bookTitle = null;
    }

    public BookNotAvailableException(String bookId, String bookTitle) {
        super(String.format("Book '%s' (ID: %s) is currently not available for borrowing", bookTitle, bookId));
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }
}