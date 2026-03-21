package com.example.Myspring_homework.repository;

import com.example.Myspring_homework.entity.BookDetail;
import com.example.Myspring_homework.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {

    Optional<BookDetail> findByBooksId(Long bookId);

    @Query("SELECT b FROM Books b LEFT JOIN FETCH b.bookDetail WHERE b.id = :id")
    Optional<BookDetail> findByIdWithBook(@Param("id") Long id);

    List<BookDetail> findByPublisher(String publisher);
}