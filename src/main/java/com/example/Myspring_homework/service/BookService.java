package com.example.Myspring_homework.service;

import com.example.Myspring_homework.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto BookCreateRequest(BookDto bookDto);

    BookDto getBookById(Long bookId);

    List<BookDto> getAllBook();

    BookDto BookUpdateRequest(Long bookId, BookDto BookUpdateRequest);

    void BookResponse(Long bookId);
}