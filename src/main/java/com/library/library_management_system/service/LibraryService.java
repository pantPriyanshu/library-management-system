package com.library.library_management_system.service;
import com.library.library_management_system.dto.BorrowRequest;
import com.library.library_management_system.entity.Book;
import com.library.library_management_system.entity.Member;
import com.library.library_management_system.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LibraryService {

    private final BookService bookService;
    private final MemberService memberService;

    public String borrowBook(BorrowRequest borrowRequest) {
        log.info("Processing borrow request - Member ID: {}, Book ID: {}",
                borrowRequest.getMemberId(), borrowRequest.getBookId());

        Member member = memberService.getMemberById(borrowRequest.getMemberId());
        Book book = bookService.getBookById(borrowRequest.getBookId());

        // Check if member can borrow more books
        if (!member.canBorrowMoreBooks()) {
            throw new BorrowLimitExceededException(
                    member.getName(), member.getBorrowedBooksCount(), 3);
        }

        // Check if book is available
        if (!book.getIsAvailable()) {
            throw new BookNotAvailableException(book.getBookId(), book.getTitle());
        }

        // Borrow the book
        member.addBorrowedBook(book);

        String message = String.format("Book '%s' has been successfully borrowed by member '%s'",
                book.getTitle(), member.getName());
        log.info(message);
        return message;
    }

    public String returnBook(BorrowRequest returnRequest) {
        log.info("Processing return request - Member ID: {}, Book ID: {}",
                returnRequest.getMemberId(), returnRequest.getBookId());

        Member member = memberService.getMemberById(returnRequest.getMemberId());
        Book book = bookService.getBookById(returnRequest.getBookId());

        // Check if the book is actually borrowed by this member
        if (!member.getBorrowedBooks().contains(book)) {
            throw new BookNotBorrowedException(book.getTitle(), member.getName());
        }

        // Return the book
        member.removeBorrowedBook(book);

        String message = String.format("Book '%s' has been successfully returned by member '%s'",
                book.getTitle(), member.getName());
        log.info(message);
        return message;
    }
}