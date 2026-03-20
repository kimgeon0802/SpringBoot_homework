package com.example.Myspring_homework.repository;

import com.example.Myspring_homework.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books,Long> {
    // ISBN으로 책을 찾기
    Optional<Books> findByIsbn(String isbn);

    // 저자로 책 찾기
    List<Books> findByAuthor(String author);

    List<Books> findByAuthorContainingIgnoreCase(String author);

    // 책 ID와 BookDetail을 함께 조회
    @Query("SELECT b FROM Books b JOIN FETCH b.bookDetail WHERE b.id = :id")
    Optional<Books> findByIdWithBookDetail(Long id);

    // ISBN과 BookDetail을 함께 조회
    @Query("SELECT b FROM Books b JOIN FETCH b.bookDetail WHERE b.isbn = :isbn")
    Optional<Books> findByIsbnWithBookDetail(String isbn);

    // ISBN이 이미 존재하는지 확인하는 메서드
    boolean existsByIsbn(String isbn);  // 특정 ISBN이 이미 존재하는지 확인
}
