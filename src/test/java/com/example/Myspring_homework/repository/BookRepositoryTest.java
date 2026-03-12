package com.example.Myspring_homework.repository;

import com.example.Myspring_homework.entity.Books;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
//@Transactional
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    //1. book 등록
    @Test
    @Rollback(value = false)
    @Disabled
    void testCreate() {
        //Given(준비단계)
        Books book = new Books();
        book.setTitle("스프링 부트 입문");
        book.setAuthor("홍길동");
        book.setIsbn("9788956746425");
        book.setPrice(30000);
        book.setPublishDate(LocalDate.ofEpochDay(2025-05-07));
        //When(실행단계)
        Books addBook = bookRepository.save(book);
        //Then(검증단계)
        assertThat(addBook).isNotNull();
        assertThat(addBook.getAuthor()).isEqualTo("홍길동");

    }

    //2. Book 조회
    @Test
    void testFindBy() {
        Optional<Books> optionalCustomer = bookRepository.findById(1L);
        if(optionalCustomer.isPresent()) {
            Books book = optionalCustomer.get();
            assertThat(book.getId()).isEqualTo(1L);
        }else{
            System.out.println("Book Not Found");
        }
        //ifPresent(Consumer)
        //Consumer의 추상메서드는 void accept(T t)
        optionalCustomer.ifPresent(book -> System.out.println(book.getAuthor()));

    }

    @Test
    @Disabled
    void testFindByNotFound() {
        //orElseGet(Supplier)
        //Supplier의 추상메서드는 T get()
        Books existCustomer = bookRepository.findById(2L)
                .orElseGet(() -> new Books());
        assertThat(existCustomer.getId()).isNull();
        //assertThat(existCustomer.getId()).isEqualTo(2L);

        //public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)
        Books notFoundBook = bookRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
    }
}