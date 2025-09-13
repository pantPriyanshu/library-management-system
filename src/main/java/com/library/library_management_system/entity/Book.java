package com.library.library_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "borrowedBy") // Exclude to avoid circular references
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @Id
    @Column(name = "book_id")
    @EqualsAndHashCode.Include
    @Builder.Default
    private String bookId = UUID.randomUUID().toString();

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String author;

    @Column(name = "is_available", nullable = false)
    @Builder.Default
    private Boolean isAvailable = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrowed_by_member_id")
    @JsonIgnore
    private Member borrowedBy;

    // Custom constructor for convenience
    public Book(String title, String author) {
        this.bookId = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}