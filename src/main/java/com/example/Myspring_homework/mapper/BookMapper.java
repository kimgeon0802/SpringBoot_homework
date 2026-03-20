package com.example.Myspring_homework.mapper;

import com.example.Myspring_homework.dto.BookDto;
import com.example.Myspring_homework.entity.BookDetail;
import com.example.Myspring_homework.entity.Books;

public class BookMapper {

    // Request -> Entity
    public static Books toEntity(BookDto.BookCreateRequest dto) {
        // Book 엔티티 생성
        Books book = Books.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .price(dto.getPrice())
                .publishDate(dto.getPublishDate())
                .build();

        // BookDetail 연결 (Optional)
        if (dto.getDetailRequest() != null) {
            // BookDetail 엔티티 생성
            BookDetail detail = BookDetail.builder()
                    .description(dto.getDetailRequest().getDescription())
                    .language_(dto.getDetailRequest().getLanguage_())
                    .pageCount(dto.getDetailRequest().getPageCount())
                    .publisher(dto.getDetailRequest().getPublisher())
                    .coverImageUrl(dto.getDetailRequest().getCoverImageUrl())
                    .edition(dto.getDetailRequest().getEdition())
                    .books(book)
                    .build();

            // BookDetail을 Books 엔티티에 연결
            book.setBookDetail(detail);
        }

        return book;  // Entity 반환
    }

    // Entity -> Response (Book 또는 BookDetail 처리)
    public static BookDto.BookResponse toResponse(Books book) {
        // BookDetailResponse는 optional하게 처리
        BookDto.BookDetailResponse detailResponse = null;

        // BookDetail이 존재할 경우에만 처리
        if (book.getBookDetail() != null) {
            detailResponse = BookDto.BookDetailResponse.builder()
                    .description(book.getBookDetail().getDescription())
                    .language_(book.getBookDetail().getLanguage_())
                    .pageCount(book.getBookDetail().getPageCount())
                    .publisher(book.getBookDetail().getPublisher())
                    .coverImageUrl(book.getBookDetail().getCoverImageUrl())
                    .edition(book.getBookDetail().getEdition())
                    .build();
        }

        return BookDto.BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .publishDate(book.getPublishDate())
                .detail(detailResponse)  // BookDetailResponse를 포함
                .build();
    }

    public static BookDto.BookDetailResponse toResponse(BookDetail bookDetail) {
        return BookDto.BookDetailResponse.builder()
                .description(bookDetail.getDescription())
                .language_(bookDetail.getLanguage_())
                .pageCount(bookDetail.getPageCount())
                .publisher(bookDetail.getPublisher())
                .coverImageUrl(bookDetail.getCoverImageUrl())
                .edition(bookDetail.getEdition())
                .build();
    }
}