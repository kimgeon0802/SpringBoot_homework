package com.example.Myspring_homework.service.impl;

import com.example.Myspring_homework.dto.BookDto;
import com.example.Myspring_homework.entity.BookDetail;
import com.example.Myspring_homework.entity.Books;
import com.example.Myspring_homework.mapper.BookMapper;
import com.example.Myspring_homework.repository.BookDetailRepository;
import com.example.Myspring_homework.repository.BookRepository;
import com.example.Myspring_homework.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.Myspring_homework.service.common.CommonService.getNotFoundExceptionSupplier;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDetailRepository bookDetailRepository;

    @Transactional(readOnly = true)
    @Override
    public BookDto.BookResponse BookCreateRequest(BookDto.BookCreateRequest bookDto) {
        //DTO => Entity 변환
        Books book = BookMapper.toEntity(bookDto);
        //등록 처리
        Books savedBook = bookRepository.save(book);
        //등록된 Entity => DTO 변환
        return BookMapper.toResponse(savedBook);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto.BookDetailResponse> getByPublisher(String publisher) {
        List<BookDetail> bookDetails = bookDetailRepository.findByPublisher(publisher);
        return bookDetails.stream()
                .map(BookMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto.BookResponse getBookById(Long bookId) {
        // 책과 책의 세부 정보(BookDetail)도 함께 조회
        Books book = bookRepository.findByIdWithBookDetail(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        // Book -> DTO로 변환
        return BookMapper.toResponse(book);
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto.BookResponse getBookByIsbn(String bookIsbn) {
        return bookRepository.findByIsbnWithBookDetail(bookIsbn)
                .map(BookMapper::toResponse)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Book is not exists with a given id: ", bookIsbn)
                );
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto.BookResponse> getBookByAuthor(String author) {
        // Publisher로 책 조회 (BookDetailRepository에서)
        List<Books> books = bookRepository.findByAuthor(author);

        return books.stream()
                .map(BookMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto.BookResponse> getAllBook() {
        // 모든 Book과 BookDetail을 패치 조인으로 함께 가져오기
        List<Books> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto.BookResponse BookUpdateRequest(Long bookId, BookDto.BookUpdateRequest BookUpdateRequest) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Book is not exists with a given id:", bookId)
                );
        //setter 호출
        book.setAuthor(BookUpdateRequest.getAuthor());
        book.setPrice(BookUpdateRequest.getPrice());
        book.setTitle(BookUpdateRequest.getTitle());
        book.setPublishDate(BookUpdateRequest.getPublishDate());

        //Department savedDepartment = departmentRepository.save(department);

        //Entity => DTO 로 변환
        return BookMapper.toResponse(book);
    }

    @Override
    public BookDto.BookResponse BookPatchRequest(Long bookId, BookDto.BookPatchRequest BookPatchRequest) {
        Books book = bookRepository.findByIdWithBookDetail(bookId)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Book is not exists with a given id:", bookId)
                );
        //setter 호출
        book.setAuthor(BookPatchRequest.getAuthor());
        book.setPrice(BookPatchRequest.getPrice());
        book.setTitle(BookPatchRequest.getTitle());
        book.setPublishDate(BookPatchRequest.getPublishDate());

        return BookMapper.toResponse(book);
    }

    @Override
    public BookDto.BookDetailResponse BookDetailPatchRequest(Long bookId, BookDto.BookDetailPatchRequest BookDetailPatchRequest) {
        BookDetail bookdetail = bookDetailRepository.findByBooksId(bookId)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Book is not exists with a given id:", bookId)
                );
        //setter 호출
        bookdetail.setDescription(BookDetailPatchRequest.getDescription());
        bookdetail.setLanguage_(BookDetailPatchRequest.getLanguage_());
        bookdetail.setPageCount(BookDetailPatchRequest.getPageCount());
        bookdetail.setPublisher(BookDetailPatchRequest.getPublisher());
        bookdetail.setCoverImageUrl(BookDetailPatchRequest.getCoverImageUrl());
        bookdetail.setEdition(BookDetailPatchRequest.getEdition());


        return BookMapper.toResponse(bookdetail);
    }

    @Override
    public void BookResponse(Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Department is not exists with a given id:", bookId)
                );
        bookRepository.delete(book);
    }
}