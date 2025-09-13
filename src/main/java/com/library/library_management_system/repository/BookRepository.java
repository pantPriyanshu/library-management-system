package com.library.library_management_system.repository;

import com.library.library_management_system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByIsAvailable(Boolean isAvailable);

    @Query("SELECT b FROM Book b WHERE b.title ILIKE %:title%")
    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT b FROM Book b WHERE b.author ILIKE %:author%")
    List<Book> findByAuthorContainingIgnoreCase(String author);
}