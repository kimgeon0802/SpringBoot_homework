package com.example.Myspring_homework.dto;

import com.example.Myspring_homework.entity.Books;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

public class BookDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookCreateRequest {
        private Long id;

        @NotBlank(message = "Book title is required")
        private String bookTitle;

        @NotBlank(message = "Author name is required")
        private String bookAuthor;

        @NotBlank(message = "ISBN is required")
        @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$",
                message = "ISBN must be valid (10 or 13 digits, with or without hyphens)")
        private String bookIsbn;

        @PositiveOrZero(message = "Price must be positive or zero")
        private Integer bookPrice;

        @Past(message = "Publish date must be in the past")
        private LocalDate bookPublishDate;

        @Valid
        private BookDetailDTO detailRequest;

        //BookCreateRequest => Entity
        public Books toEntity() {
            Books book = new Books();
            book.setTitle(this.bookTitle);
            book.setAuthor(this.bookAuthor);
            book.setIsbn(this.bookIsbn);
            book.setPrice(this.bookPrice);
            book.setPublishDate(this.bookPublishDate);
            return book;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookUpdateRequest {
        @Positive(message = "가격은 양수여야 합니다.")
        private Integer price;

        // 확장 가능성을 위해 추가 필드들을 옵셔널하게 포함할 수 있음
        @NotBlank(message = "제목은 필수 입력 항목입니다.")
        private String title;

        @NotBlank(message = "저자는 필수 입력 항목입니다.")
        private String author;

        //@NotBlank(message = "출판일자는 필수 입력 항목입니다.")
        private LocalDate publishDate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookDetailDTO {
        private String description;
        private String language;
        private Integer pageCount;
        private String publisher;
        private String coverImageUrl;
        private String edition;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookResponse {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;
        private BookDetailResponse detail;

        public static BookResponse fromEntity(Books book) {
            BookDetailResponse detailResponse = book.getBookDetail() != null
                    ? BookDetailResponse.builder()
                    .id(book.getBookDetail().getId())
                    .description(book.getBookDetail().getDescription())
                    .language(book.getBookDetail().getLanguage_())
                    .pageCount(book.getBookDetail().getPageCount())
                    .publisher(book.getBookDetail().getPublisher())
                    .coverImageUrl(book.getBookDetail().getCoverImageUrl())
                    .edition(book.getBookDetail().getEdition())
                    .build()
                    : null;

            return BookResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .price(book.getPrice())
                    .publishDate(book.getPublishDate())
                    .detail(detailResponse)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookDetailResponse {
        private Long id;
        private String description;
        private String language;
        private Integer pageCount;
        private String publisher;
        private String coverImageUrl;
        private String edition;
    }
}