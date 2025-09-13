package com.library.library_management_system.exception;

import lombok.Getter;

@Getter
public class BookNotBorrowedException extends RuntimeException {
    private final String bookTitle;
    private final String memberName;

    public BookNotBorrowedException(String message) {
        super(message);
        this.bookTitle = null;
        this.memberName = null;
    }

    public BookNotBorrowedException(String bookTitle, String memberName) {
        super(String.format("Book '%s' is not borrowed by member '%s'", bookTitle, memberName));
        this.bookTitle = bookTitle;
        this.memberName = memberName;
    }
}