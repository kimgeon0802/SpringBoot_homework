package com.example.Myspring_homework.dto;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private LocalDate bookPublishDate;
    private Integer bookPrice;
}
