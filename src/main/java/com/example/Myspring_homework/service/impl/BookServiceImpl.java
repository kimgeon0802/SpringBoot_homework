package com.example.Myspring_homework.service.impl;

import com.example.Myspring_homework.dto.BookDto;
import com.example.Myspring_homework.entity.Books;
import com.example.Myspring_homework.mapper.BookMapper;
import com.example.Myspring_homework.repository.BookRepository;
import com.example.Myspring_homework.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.Myspring_homework.service.common.CommonService.getNotFoundExceptionSupplier;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

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
    public BookDto.BookResponse getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .map(BookMapper::toResponse)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Book is not exists with a given id: ", bookId)
                );
    }



    @Transactional(readOnly = true)
    @Override
    public List<BookDto.BookResponse> getAllBook() {

        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponse)
                .toList();
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
    public void BookResponse(Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Department is not exists with a given id:", bookId)
                );
        bookRepository.delete(book);
    }
}