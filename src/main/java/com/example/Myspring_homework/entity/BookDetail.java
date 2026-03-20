package com.example.Myspring_homework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookdetail")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", unique = true, nullable = false)
    @JsonBackReference
    private Books books;

    private String description;   // 설명
    private String language_;      // 언어
    private Integer pageCount;        // 페이지 수
    private String publisher;     // 출판사
    private String coverImageUrl; // 표지 이미지 URL
    private String edition;       // 에디션

    public void setBooks(Books books) {
        this.books = books;
        if (books.getBookDetail() != this) {
            books.setBookDetail(this);
        }
    }
}
