package com.example.Myspring_homework.runner;

import com.example.Myspring_homework.entity.Books;
import com.example.Myspring_homework.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info(">>>> 초기 데이터 등록 시작...");

        // 기존 데이터가 없을 때만 등록하도록 방어 코드 작성
        if (bookRepository.count() == 0) {
            Books book1 = createBook("자바의 정석", "남궁성", "978-89-94492-03-2", 30000, LocalDate.of(2016, 2, 1));
            Books book2 = createBook("Spring Boot 실전", "김영한", "979-11-5839-140-1", 33000, LocalDate.of(2023, 5, 20));
            Books book3 = createBook("Clean Code", "Robert C. Martin", "978-0132350884", 45000, LocalDate.of(2008, 8, 1));

            bookRepository.saveAll(List.of(book1, book2, book3));
            log.info(">>>> {}권의 도서 정보가 등록되었습니다.", bookRepository.count());
        } else {
            log.info(">>>> 이미 데이터가 존재하여 초기화를 건너뜁니다.");
        }
    }

    // 편의를 위한 헬퍼 메서드
    private Books createBook(String title, String author, String isbn, Integer price, LocalDate publishDate) {
        Books book = new Books();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setPublishDate(publishDate);
        return book;
    }
}