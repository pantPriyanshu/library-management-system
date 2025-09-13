package com.library.library_management_system.exception;

import lombok.Getter;

@Getter
public class BorrowLimitExceededException extends RuntimeException {
    private final String memberName;
    private final int currentBorrowCount;
    private final int maxAllowed;

    public BorrowLimitExceededException(String message) {
        super(message);
        this.memberName = null;
        this.currentBorrowCount = 0;
        this.maxAllowed = 3;
    }

    public BorrowLimitExceededException(String memberName, int currentBorrowCount, int maxAllowed) {
        super(String.format("Member '%s' has already borrowed %d books (maximum %d allowed)",
                memberName, currentBorrowCount, maxAllowed));
        this.memberName = memberName;
        this.currentBorrowCount = currentBorrowCount;
        this.maxAllowed = maxAllowed;
    }
}
