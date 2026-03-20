package com.example.Myspring_homework.mapper;

import com.example.Myspring_homework.dto.BookDto;
import com.example.Myspring_homework.entity.BookDetail;
import com.example.Myspring_homework.entity.Books;

public class BookMapper {

    //Request -> Entity
    public static Books toEntity(BookDto.BookCreateRequest dto) {

        Books book = Books.builder()
                .id(dto.getId())
                .title(dto.getBookTitle())
                .author(dto.getBookAuthor())
                .isbn(dto.getBookIsbn())
                .price(dto.getBookPrice())
                .publishDate(dto.getBookPublishDate())
                .build();

        //BookDetail 연결
        if (dto.getDetailRequest() != null) {
            BookDetail detail = BookDetail.builder()
                    .description(dto.getDetailRequest().getDescription())
                    .language_(dto.getDetailRequest().getLanguage())
                    .pageCount(dto.getDetailRequest().getPageCount())
                    .publisher(dto.getDetailRequest().getPublisher())
                    .coverImageUrl(dto.getDetailRequest().getCoverImageUrl())
                    .edition(dto.getDetailRequest().getEdition())
                    .books(book) // ⭐ 연관관계 핵심
                    .build();

            book.setBookDetail(detail);
        }

        return book;
    }

    // Entity -> Response
    public static BookDto.BookResponse toResponse(Books book) {

        BookDto.BookDetailResponse detailResponse = null;

        if (book.getBookDetail() != null) {
            detailResponse = BookDto.BookDetailResponse.builder()
                    .id(book.getBookDetail().getId())
                    .description(book.getBookDetail().getDescription())
                    .language(book.getBookDetail().getLanguage_())
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
                .detail(detailResponse)
                .build();
    }
}