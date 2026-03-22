package com.example.Myspring_homework.repository;

import com.example.Myspring_homework.entity.BookDetail;
import com.example.Myspring_homework.entity.Books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDetailRepository bookDetailRepository;

    @Test
    public void createBookWithBookDetail() {
        // Given
        Books book = Books.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("9780132350884")
                .price(45)
                .publishDate(LocalDate.of(2008, 8, 1))
                .build();

        BookDetail bookDetail = BookDetail.builder()
                .description("A handbook of agile software craftsmanship")
                .language_("English")
                .pageCount(464)
                .publisher("Prentice Hall")
                .coverImageUrl("https://example.com/cleancode.jpg")
                .edition("1st")
                .books(book)
                .build();

        book.setBookDetail(bookDetail);

        // When
        Books savedBook = bookRepository.save(book);

        // Then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Clean Code");
        assertThat(savedBook.getIsbn()).isEqualTo("9780132350884");
        assertThat(savedBook.getBookDetail()).isNotNull();
        assertThat(savedBook.getBookDetail().getPublisher()).isEqualTo("Prentice Hall");
        assertThat(savedBook.getBookDetail().getPageCount()).isEqualTo(464);
    }

    @Test
    public void findBookByIsbn() {
        // Given
        Books book = Books.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("9780132350884")
                .price(45)
                .publishDate(LocalDate.of(2008, 8, 1))
                .build();

        BookDetail bookDetail = BookDetail.builder()
                .description("A handbook of agile software craftsmanship")
                .language_("English")
                .pageCount(464)
                .publisher("Prentice Hall")
                .coverImageUrl("https://example.com/cleancode.jpg")
                .edition("1st")
                .books(book)
                .build();

        book.setBookDetail(bookDetail);
        bookRepository.save(book);

        // When
        Optional<Books> foundBook = bookRepository.findByIsbn("9780132350884");

        // Then
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("Clean Code");
    }

    @Test
    public void findByIdWithBookDetail() {
        // Given
        Books book = Books.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("9780132350884")
                .price(45)
                .publishDate(LocalDate.of(2008, 8, 1))
                .build();

        BookDetail bookDetail = BookDetail.builder()
                .description("A handbook of agile software craftsmanship")
                .language_("English")
                .pageCount(464)
                .publisher("Prentice Hall")
                .coverImageUrl("https://example.com/cleancode.jpg")
                .edition("1st")
                .books(book)
                .build();

        book.setBookDetail(bookDetail);
        Books savedBook = bookRepository.save(book);

        // When
        Optional<Books> foundBook = bookRepository.findByIdWithBookDetail(savedBook.getId());

        // Then
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getBookDetail()).isNotNull();
        assertThat(foundBook.get().getBookDetail().getPublisher()).isEqualTo("Prentice Hall");
    }

    @Test
    public void findBooksByAuthor() {
        // Given
        Books book1 = Books.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("9780132350884")
                .price(10000)
                .publishDate(LocalDate.of(2008, 8, 1))
                .build();

        Books book2 = Books.builder()
                .title("Clean Architecture")
                .author("Robert C. Martin")
                .isbn("9780134494166")
                .price(10000)
                .publishDate(LocalDate.of(2008, 8, 1))
                .build();

        Books book3 = Books.builder()
                .title("Effective Java")
                .author("Joshua Bloch")
                .isbn("9780134685991")
                .price(10000)
                .publishDate(LocalDate.of(2008, 8, 1))
                .build();

        bookRepository.saveAll(List.of(book1, book2, book3));

        // When
        List<Books> martinBooks = bookRepository.findByAuthorContainingIgnoreCase("martin");

        assertThat(martinBooks).hasSize(2);
        assertThat(martinBooks).extracting(Books::getTitle)
                .containsExactlyInAnyOrder("Clean Code", "Clean Architecture");
    }

    @Test
    public void findBookDetailByBookId() {
        // Given
        Books book = Books.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("9780132350884")
                .price(45)
                .publishDate(LocalDate.of(2008, 8, 1))
                .build();

        BookDetail bookDetail = BookDetail.builder()
                .description("A handbook of agile software craftsmanship")
                .language_("English")
                .pageCount(464)
                .publisher("Prentice Hall")
                .coverImageUrl("https://example.com/cleancode.jpg")
                .edition("1st")
                .books(book)
                .build();

        book.setBookDetail(bookDetail);
        Books savedBook = bookRepository.save(book);

        // When
        Optional<BookDetail> foundBookDetail = bookDetailRepository.findByBooksId(savedBook.getId());

        // Then
        assertThat(foundBookDetail).isPresent();
        assertThat(foundBookDetail.get().getDescription()).contains("agile software craftsmanship");
    }
}