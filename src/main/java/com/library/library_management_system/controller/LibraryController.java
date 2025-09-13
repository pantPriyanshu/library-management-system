package com.library.library_management_system.controller;

import com.library.library_management_system.dto.BorrowRequest;
import com.library.library_management_system.service.LibraryService;
import com.library.library_management_system.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping("/borrow")
    public ResponseEntity<ApiResponse> borrowBook(@Valid @RequestBody BorrowRequest borrowRequest) {
        log.info("POST /api/library/borrow - Processing borrow request");
        String message = libraryService.borrowBook(borrowRequest);

        ApiResponse response = ApiResponse.builder()
                .message(message)
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/return")
    public ResponseEntity<ApiResponse> returnBook(@Valid @RequestBody BorrowRequest returnRequest) {
        log.info("POST /api/library/return - Processing return request");
        String message = libraryService.returnBook(returnRequest);

        ApiResponse response = ApiResponse.builder()
                .message(message)
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }
}