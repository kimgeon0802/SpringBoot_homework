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

    @Override
    public BookDto BookCreateRequest(BookDto bookDto) {
        //DTO => Entity 변환
        Books book = BookMapper.mapToBook(bookDto);
        //등록 처리
        Books savedBook = bookRepository.save(book);
        //등록된 Entity => DTO 변환
        return BookMapper.mapToBookDto(savedBook);
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto getBookById(Long bookId) {
        return bookRepository.findById(bookId) //Optional<Department>
                //.map(book-> BookMapper.mapToBookDto(book))
                .map(BookMapper::mapToBookDto) //Optional<BookDto)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Book is not exists with a given id: ", bookId)
                );
    }



    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllBook() {
        List<Books> books = bookRepository.findAll();
        // List<boos> => Stream<boos>
        return books.stream() //Stream<boos>
                //.map(boos -> BooksMapper.mapToBookDto(books)) //Stream<bookDto>
                .map(BookMapper::mapToBookDto)  //Stream<DepartmentDto>
        // Stream<BookDto> => List<BookDto>
                .toList();
    }

    @Override
    public BookDto BookUpdateRequest(Long bookId, BookDto BookUpdateRequest) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(getNotFoundExceptionSupplier(
                        "Book is not exists with a given id:", bookId)
                );
        //setter 호출
        book.setAuthor(BookUpdateRequest.getBookAuthor());
        book.setPrice(BookUpdateRequest.getBookPrice());
        book.setTitle(BookUpdateRequest.getBookTitle());
        book.setPublishDate(BookUpdateRequest.getBookPublishDate());

        //Department savedDepartment = departmentRepository.save(department);

        //Entity => DTO 로 변환
        return BookMapper.mapToBookDto(book);
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
