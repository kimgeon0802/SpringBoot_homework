package com.example.Myspring_homework.dto;

import com.example.Myspring_homework.entity.Books;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

public class BookDto {

    // 생성 시 필요한 DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookCreateRequest {
        private Long id;

        @NotBlank(message = "Book title is required")
        private String title;

        @NotBlank(message = "Author name is required")
        private String author;

        @NotBlank(message = "ISBN is required")
        @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$",
                message = "ISBN must be valid (10 or 13 digits, with or without hyphens)")
        private String isbn;

        @PositiveOrZero(message = "Price must be positive or zero")
        private Integer price;

        @Past(message = "Publish date must be in the past")
        private LocalDate publishDate;

        @Valid
        private BookDetailDTO detailRequest;  // 상세 정보 추가
    }

    // 응답 시 필요한 DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BookResponse {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;
        private BookDetailResponse detail;  // 상세 응답 포함
    }

    // 도서 수정 시 요청하는 DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookUpdateRequest {
        @Positive(message = "Price must be positive")
        private Integer price;

        @NotBlank(message = "Title is required")
        private String title;

        @NotBlank(message = "Author is required")
        private String author;

        private LocalDate publishDate;  // 수정 시 변경될 수 있는 필드
    }

    // 부분 수정 요청에 필요한 DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookPatchRequest {
        @Positive(message = "Price must be positive")
        private Integer price;

        @NotBlank(message = "Title is required")
        private String title;

        @NotBlank(message = "Author is required")
        private String author;

        private LocalDate publishDate;  // 수정할 필드만
    }

    // 도서 상세 수정 요청에 필요한 DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookDetailPatchRequest {
        private String description;  // String 타입
        private String language_;    // String 타입
        private int pageCount;       // int 타입
        private String publisher;    // String 타입
        private String coverImageUrl; // String 타입
        private String edition;      // String 타입
    }

    // 도서 상세 정보 전송을 위한 DTO (등록 시 사용)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookDetailDTO {
        private String description;
        private String language_;
        private Integer pageCount;
        private String publisher;
        private String coverImageUrl;
        private String edition;
    }

    // 도서 상세 응답 DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookDetailResponse {
        private String description;
        private String language_;
        private Integer pageCount;
        private String publisher;
        private String coverImageUrl;
        private String edition;
    }
}