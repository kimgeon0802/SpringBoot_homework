package com.example.Myspring_homework.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;

    @NotBlank(message = "책 제목은 필수 입력 항목입니다.")
    private String bookTitle;

    @NotBlank(message = "책 저자는 필수 입력 항목입니다.")
    private String bookAuthor;

    @NotBlank(message = "도서번호 필수 입력 항목입니다.")
    private String bookIsbn;

    @NotBlank(message = "출판날짜는 필수 입력 항목입니다.")
    private LocalDate bookPublishDate;

    @NotNull(message = "가격은 필수 입력 항목입니다.")
    @Positive(message = "올바른 형식이 아닙니다.")
    private Integer bookPrice;
}
