package com.example.Myspring_homework.service;

import com.example.Myspring_homework.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDto.BookResponse BookCreateRequest(BookDto.BookCreateRequest request);

    BookDto.BookResponse getBookById(Long bookId);

    BookDto.BookResponse BookPatchRequest(Long bookId, BookDto.BookPatchRequest BookPatchRequest);

    BookDto.BookDetailResponse BookDetailPatchRequest(Long bookId, BookDto.BookDetailPatchRequest BookDetailPatchRequest);

    List<BookDto.BookDetailResponse> getByPublisher(String Publisher);

    BookDto.BookResponse getBookByIsbn(String bookIsbn);

    List<BookDto.BookResponse> getBookByAuthor(String bookauthor);

    List<BookDto.BookResponse> getAllBook();

    BookDto.BookResponse BookUpdateRequest(Long bookId, BookDto.BookUpdateRequest request);

    void BookResponse(Long bookId);
}