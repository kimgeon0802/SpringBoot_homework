package com.example.Myspring_homework.repository;

import com.example.Myspring_homework.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Books,Long> {
    List<Books> findByIsbn(String isbn);
    Optional<Books> findByAuthor(String author);
}