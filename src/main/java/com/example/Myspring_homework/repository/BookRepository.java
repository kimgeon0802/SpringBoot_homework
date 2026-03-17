package com.example.Myspring_homework.repository;

import com.example.Myspring_homework.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books,Long> {
    //finder(query) method
    //1. Isbn(도서번호)로 조회하는 finder 메서드
    Optional<Books> findByIsbn(String isbn);

    //2. Author(저자, 중복허용함)로 조회하는 finder 메서드 % name %
    List<Books> findByAuthor(String author);

    //3. ID(ID, 중복허용함)로 조회하는 finder 메서드 % name %
    //Optional<Books> findById(Long id);
}