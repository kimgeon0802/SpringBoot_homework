package com.example.Myspring_homework.controller;

import com.example.Myspring_homework.entity.Books;
import com.example.Myspring_homework.exception.BusinessException;
import com.example.Myspring_homework.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {
    private final BookRepository bookRepository;

    //도서 등록 POST
    @PostMapping
    public Books create(@RequestBody Books bookDetail) {
        return bookRepository.save(bookDetail);
    }

    //도서 목록조회 GET
    @GetMapping
    public List<Books> getBook() {
        return bookRepository.findAll();
    }

    //ID로 Book 조회 GET
    @GetMapping("/{id}")
    public Books getBookById(@PathVariable Long id) {
        Optional<Books> OptionalBook = bookRepository.findById(id);
        Books existBook = getExistBook(OptionalBook);
        return existBook;
    }

    //도서번호 조회하고 Book 수정하기 GET PUT
    @PatchMapping("/{isbn}/")
    public Books updateBook(@PathVariable String isbn, @RequestBody Books bookDetail) {
        Books existBook = getExistBook(bookRepository.findByIsbn(isbn));
        existBook.setTitle(bookDetail.getTitle());
        return bookRepository.save(existBook);
    }

    //Book 삭제하기 DEL
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Optional<Books> ListBook = bookRepository.findById(id);
        Books book = getExistBook(ListBook);
        bookRepository.delete(book);
        return ResponseEntity.ok("도서ID = " + id + " Book이 삭제 되었습니다.");
    }
    //private 공통 메서드
    private static Books getExistBook(Optional<Books> OptionalBook) {
        Books existBook = OptionalBook
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return existBook;
    }

}