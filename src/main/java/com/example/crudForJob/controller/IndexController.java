package com.example.crudForJob.controller;


import com.example.crudForJob.model.Dto.ADto;
import com.example.crudForJob.model.Dto.BookDto;
import com.example.crudForJob.model.Dto.PublisherDto;
import com.example.crudForJob.service.AuthorService;
import com.example.crudForJob.service.BookService;
import com.example.crudForJob.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/authors")
    public String getProduct(){
        return "authors";
    }

    @RequestMapping("/book/new")
    public String newProduct(Model model){
        List<PublisherDto> dtos = publisherService.getAll();
        List<ADto> aDtos = authorService.getAll();
        model.addAttribute("publishers", dtos);
        model.addAttribute("authors", aDtos);
        return "booksform";
    }

    @GetMapping("/add-publisher")
    public String addPublisher(Model model){
        return "publisher";
    }

    @GetMapping("/add-author")
    public String addAuthor(Model model){
        return "authorAdd";
    }

    @GetMapping("/book/edit/{bookId}")
    public String addAuthorForBook(Model model, @PathVariable(value = "bookId") Long bookId){
        BookDto bookDto = bookService.findDtoById(bookId);
        List<ADto> aDtos = authorService.getAll();
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("authors", aDtos);
        return "authorForBook";
    }
}
