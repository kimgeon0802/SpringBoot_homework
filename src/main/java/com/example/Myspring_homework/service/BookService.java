package com.example.Myspring_homework.service;

import com.example.Myspring_homework.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto.BookResponse BookCreateRequest(BookDto.BookCreateRequest request);

    BookDto.BookResponse getBookById(Long bookId);

    List<BookDto.BookResponse> getAllBook();

    BookDto.BookResponse BookUpdateRequest(Long bookId, BookDto.BookUpdateRequest request);

    void BookResponse(Long bookId);
}