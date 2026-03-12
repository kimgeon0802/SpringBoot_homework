package com.example.Myspring_homework.controller;

import com.example.Myspring_homework.entity.Books;
import com.example.Myspring_homework.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("/thymeleaf")
    public String leaf(Model model) {
        model.addAttribute("name","스프링부트");
        return "leaf";
    }

    @GetMapping("/index")
    public ModelAndView index() {
        List<Books> bookList = bookRepository.findAll();
        return new ModelAndView("index","books",bookList);
    }


    //등록 페이지를 Load하는 메서드
    @GetMapping("/signup")
    public String showSignUpForm(@ModelAttribute("bookForm") Books book) {
        return "add-book";
    }
    //입력항목을 검증하고 등록처리를 하는 메서드
    @PostMapping("/addbook")
    public String addBook(@Valid @ModelAttribute("bookForm") Books book,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        bookRepository.save(book);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Books user = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("bookForm", user);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @Valid @ModelAttribute("userForm") Books book,
                             BindingResult result) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-user";
        }
        bookRepository.save(book);
        return "redirect:/index";
    }

}