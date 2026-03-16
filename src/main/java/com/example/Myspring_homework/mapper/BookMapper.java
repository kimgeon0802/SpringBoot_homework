package com.example.Myspring_homework.mapper;

import com.example.Myspring_homework.dto.BookDto;
import com.example.Myspring_homework.entity.Books;

public class BookMapper {
    // Entity -> DTO
    public static BookDto mapToBookDto(Books Book){
        return BookDto.builder()
                .id(Book.getId())
                .bookTitle(Book.getTitle())
                .bookAuthor(Book.getAuthor())
                .bookIsbn(Book.getIsbn())
                .bookPublishDate(Book.getPublishDate())
                .bookPrice(Book.getPrice())
                .build();
    }

    // DTO -> Entity
    public static Books mapToBook(BookDto bookDto){
        return Books.builder()
                .id(bookDto.getId())
                .title(bookDto.getBookTitle())
                .isbn(bookDto.getBookIsbn())
                .author(bookDto.getBookAuthor())
                .publishDate(bookDto.getBookPublishDate())
                .price(bookDto.getBookPrice())
                .build();
    }
}