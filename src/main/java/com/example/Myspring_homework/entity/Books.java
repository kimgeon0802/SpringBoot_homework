package com.example.Myspring_homework.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "books")
@Getter @Setter
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private LocalDate publishDate;

    @Column(nullable = false)
    private Integer price;

    @OneToOne(mappedBy = "books", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BookDetail bookDetail;

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
        if (bookDetail != null) {
            bookDetail.setBooks(this);
        }
    }
}