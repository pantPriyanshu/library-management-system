package com.library.library_management_system.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}