package com.library.library_management_system.config;

import com.library.library_management_system.entity.Book;
import com.library.library_management_system.entity.Member;
import com.library.library_management_system.repository.BookRepository;
import com.library.library_management_system.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0 && memberRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        log.info("Loading sample data...");
        
        // Create sample books using Lombok builder
        Book book1 = Book.builder()
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .isAvailable(true)
                .build();
                
        Book book2 = Book.builder()
                .title("To Kill a Mockingbird")
                .author("Harper Lee")
                .isAvailable(true)
                .build();
                
        Book book3 = Book.builder()
                .title("1984")
                .author("George Orwell")
                .isAvailable(true)
                .build();
                
        Book book4 = Book.builder()
                .title("Pride and Prejudice")
                .author("Jane Austen")
                .isAvailable(true)
                .build();
                
        Book book5 = Book.builder()
                .title("The Catcher in the Rye")
                .author("J.D. Salinger")
                .isAvailable(true)
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);

        // Create sample members using Lombok builder
        Member member1 = Member.builder()
                .name("John Doe")
                .build();
                
        Member member2 = Member.builder()
                .name("Jane Smith")
                .build();
                
        Member member3 = Member.builder()
                .name("Michael Johnson")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        log.info("Sample data loaded successfully!");
        log.info("Books: {}", bookRepository.count());
        log.info("Members: {}", memberRepository.count());
    }
}


