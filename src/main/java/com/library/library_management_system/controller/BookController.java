package com.library.library_management_system.controller;

import com.library.library_management_system.dto.BookRequest;
import com.library.library_management_system.entity.Book;
import com.library.library_management_system.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("GET /api/books - Fetching all books");
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId) {
        log.info("GET /api/books/{} - Fetching book by ID", bookId);
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        log.info("GET /api/books/available - Fetching available books");
        List<Book> availableBooks = bookService.getAvailableBooks();
        return ResponseEntity.ok(availableBooks);
    }

    @GetMapping("/borrowed")
    public ResponseEntity<List<Book>> getBorrowedBooks() {
        log.info("GET /api/books/borrowed - Fetching borrowed books");
        List<Book> borrowedBooks = bookService.getBorrowedBooks();
        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookRequest bookRequest) {
        log.info("POST /api/books - Adding new book");
        Book savedBook = bookService.addBook(bookRequest);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId,
            @Valid @RequestBody BookRequest bookRequest) {
        log.info("PUT /api/books/{} - Updating book", bookId);
        Book updatedBook = bookService.updateBook(bookId, bookRequest);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        log.info("DELETE /api/books/{} - Deleting book", bookId);
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        log.info("GET /api/books/search - Searching books with title: '{}', author: '{}'", title, author);
        List<Book> books;
        if (title != null && !title.trim().isEmpty()) {
            books = bookService.searchBooksByTitle(title);
        } else if (author != null && !author.trim().isEmpty()) {
            books = bookService.searchBooksByAuthor(author);
        } else {
            books = bookService.getAllBooks();
        }
        return ResponseEntity.ok(books);
    }
}
