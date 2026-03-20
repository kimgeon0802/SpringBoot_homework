package com.example.Myspring_homework.controller;

import com.example.Myspring_homework.dto.BookDto;
import com.example.Myspring_homework.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 모든 도서 조회
    @GetMapping
    public ResponseEntity<List<BookDto.BookResponse>> getAllBooks() {
        List<BookDto.BookResponse> books = bookService.getAllBook();
        return ResponseEntity.ok(books);
    }

    // ID로 도서 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookDto.BookResponse> getBookById(@PathVariable Long id) {
        BookDto.BookResponse book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // ISBN으로 도서 조회
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDto.BookResponse> getBookByIsbn(@PathVariable String isbn) {
        BookDto.BookResponse book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    // 저자명으로 도서 조회
    @GetMapping("/api/books/search/author?author={author}")
    public ResponseEntity<List<BookDto.BookResponse>> getBookByAuthor(@PathVariable String author) {
        List<BookDto.BookResponse> books = bookService.getBookByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/api/books/search/title?title={title}")
    public ResponseEntity<List<BookDto.BookResponse>> getBookByTitle(@PathVariable String title) {
        List<BookDto.BookResponse> books = bookService.getBookByAuthor(title);
        return ResponseEntity.ok(books);
    }

    // 도서 등록
    @PostMapping
    public ResponseEntity<BookDto.BookResponse> BookCreateRequest(@Valid @RequestBody BookDto.BookCreateRequest request) {
        BookDto.BookResponse BookCreateRequest = bookService.BookCreateRequest(request);
        return new ResponseEntity<>(BookCreateRequest, HttpStatus.CREATED);
    }

    // 도서 정보 전체 수정
    @PutMapping("/{id}")
    public ResponseEntity<BookDto.BookResponse> BookCreateRequest(
            @PathVariable Long id,
            @Valid @RequestBody BookDto.BookUpdateRequest request) {
        BookDto.BookResponse updatedBook = bookService.BookUpdateRequest(id, request);
        return ResponseEntity.ok(updatedBook);
    }

    // 도서 정보 부분 수정(기본정보)
    @PatchMapping("/{id}")
    public ResponseEntity<BookDto.BookResponse> PatchRequest(
            @PathVariable Long id,
            @Valid @RequestBody BookDto.BookPatchRequest request) {
        BookDto.BookResponse patchBook = bookService.BookPatchRequest(id, request);
        return ResponseEntity.ok(patchBook);
    }

    // 도서 정보 부분 수정(상세정보)
    @PatchMapping("/{id}/detail")
    public ResponseEntity<BookDto.BookDetailResponse> BookDetailPatchRequest(
            @PathVariable Long id,
            @Valid @RequestBody BookDto.BookDetailPatchRequest request) {
        BookDto.BookDetailResponse patchBook = bookService.BookDetailPatchRequest(id, request);
        return ResponseEntity.ok(patchBook);
    }

    // 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> BookResponse(@PathVariable Long id) {
        bookService.BookResponse(id);
        return ResponseEntity.noContent().build();
    }
}