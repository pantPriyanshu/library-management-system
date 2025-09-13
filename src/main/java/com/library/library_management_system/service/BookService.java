package com.library.library_management_system.service;

import com.library.library_management_system.dto.BookRequest;
import com.library.library_management_system.entity.Book;
import com.library.library_management_system.exception.ResourceNotFoundException;
import com.library.library_management_system.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        log.info("Fetching all books");
        return bookRepository.findAll();
    }

    public Book getBookById(String bookId) {
        log.info("Fetching book with ID: {}", bookId);
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "ID", bookId));
    }

    public List<Book> getAvailableBooks() {
        log.info("Fetching available books");
        return bookRepository.findByIsAvailable(true);
    }

    public List<Book> getBorrowedBooks() {
        log.info("Fetching borrowed books");
        return bookRepository.findByIsAvailable(false);
    }

    public Book addBook(BookRequest bookRequest) {
        log.info("Adding new book: {} by {}", bookRequest.getTitle(), bookRequest.getAuthor());
        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .isAvailable(true)
                .build();
        return bookRepository.save(book);
    }

    public Book updateBook(String bookId, BookRequest bookRequest) {
        log.info("Updating book with ID: {}", bookId);
        Book existingBook = getBookById(bookId);
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setAuthor(bookRequest.getAuthor());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(String bookId) {
        log.info("Deleting book with ID: {}", bookId);
        Book book = getBookById(bookId);
        bookRepository.delete(book);
    }

    public List<Book> searchBooksByTitle(String title) {
        log.info("Searching books by title: {}", title);
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        log.info("Searching books by author: {}", author);
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
}