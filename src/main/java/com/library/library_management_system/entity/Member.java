package com.library.library_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "borrowedBooks") // Exclude to avoid lazy loading issues
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @EqualsAndHashCode.Include
    private Long memberId;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "borrowedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Book> borrowedBooks = new ArrayList<>();

    // Custom constructor for convenience
    public Member(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Business logic methods
    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
        book.setBorrowedBy(this);
        book.setIsAvailable(false);
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
        book.setBorrowedBy(null);
        book.setIsAvailable(true);
    }

    public boolean canBorrowMoreBooks() {
        return borrowedBooks.size() < 3;
    }

    public int getBorrowedBooksCount() {
        return borrowedBooks.size();
    }
}