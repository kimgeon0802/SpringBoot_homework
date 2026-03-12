package com.example.Myspring_homework.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "제목은 필수 입력항목입니다.")
    @Column(nullable = false)
    private String title;

    @NotEmpty(message = "저자는 필수 입력항목입니다.")
    @Column(nullable = false)
    private String author;

    @NotEmpty(message = "도서번호는 필수 입력항목입니다.")
    @Column(nullable = false)
    private String isbn;

    @NotEmpty(message = "게시일은 필수 입력항목입니다.")
    @Column(nullable = false)
    private LocalDate publishDate;

    @NotBlank(message = "가격은 필수 입력항목입니다.")
    @Column(unique = true, nullable = false)
    private Integer price;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
}