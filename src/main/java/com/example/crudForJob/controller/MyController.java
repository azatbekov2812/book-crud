package com.example.crudForJob.controller;

import com.example.crudForJob.Exception.AuthorAddException;
import com.example.crudForJob.Exception.BookException;
import com.example.crudForJob.model.Dto.*;
import com.example.crudForJob.model.Entity.Author;
import com.example.crudForJob.model.Entity.Book;
import com.example.crudForJob.service.AuthorService;
import com.example.crudForJob.service.BookService;
import com.example.crudForJob.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;


@Controller
public class MyController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/save-book")
    public String saveBook(@RequestParam(value = "bookName") String bookName, @RequestParam(value = "price") BigDecimal price,
                           @RequestParam(value = "publisherName") String publisherName, @RequestParam(value = "description") String description,
                           @RequestParam(value = "authorName") String authorName, Model model) {
        ExceptionDto errorDto = null;
        try {
            bookService.saveBook(bookName, publisherName, price, description, authorName);
        } catch (BookException e) {
            errorDto = new ExceptionDto();
            errorDto.setBookexcep(e.getMessage());
        } catch (Exception e) {
            errorDto = new ExceptionDto(e.getMessage());
        }

        List<PublisherDto> dtos = publisherService.getAll();
        List<ADto> aDtos = authorService.getAll();
        model.addAttribute("publishers", dtos);
        model.addAttribute("authors", aDtos);
        model.addAttribute("error", errorDto);
        return "booksform";
    }

    @GetMapping("/all-books")
    public String getAllAuthor(Model model) {
        List<BookDto> bookDtos = bookService.getAllBook(); //true
        List<ADto> aDtos = authorService.getAll();
        model.addAttribute("bookDtos", bookDtos);
        model.addAttribute("aDtos", aDtos);
        //return new ResponseEntity(bookDtos, HttpStatus.OK);
        return "authors";
    }

    @GetMapping("/test-show-books")
    public String getTestAllAuthor(Model model, @RequestParam(value = "authorName") String authorName,
                                   @RequestParam(value = "bookName") String bookName) {

        List<BookDto> bookDtos = bookService.getTestAllBook(bookName, authorName); //true
        List<ADto> aDtos = authorService.getAll();
        List<BDto> bDtos = bookService.getAll();
        model.addAttribute("bookDtos", bookDtos);
        model.addAttribute("aDtos", aDtos);
        model.addAttribute("bDtos", bDtos);
        return "test-authors";
    }

    @GetMapping("/primer")
    public String getTest(Model model) {
        List<BookDto> bookDtos = bookService.getAllBook(); //true
        List<ADto> aDtos = authorService.getAll();
        model.addAttribute("bookDtos", bookDtos);
        model.addAttribute("aDtos", aDtos);
        return "test";
    }

    @GetMapping("/save-publisher")
    public String savePublisher(@RequestParam(value = "publisherName") String publisherName, Model model) throws Exception {
        ExceptionDto errorDto = null;
        try {
            publisherService.savePublisher(publisherName);
        } catch (Exception e) {
            errorDto = new ExceptionDto(e.getMessage());
        }
        model.addAttribute("error", errorDto);
        return "publisher";
    }

    @GetMapping("/save-author")
    public String saveAuthor(@RequestParam(value = "authorName") String authorName, Model model) {
        ExceptionDto errorDto = null;
        try {
            authorService.saveAuthor(authorName);
        } catch (Exception e) {
            errorDto = new ExceptionDto(e.getMessage());
        }
        model.addAttribute("error", errorDto);
        return "authorAdd";
    }

    // конкретная книга
    @GetMapping("/author/{id}/book/{bookName}")
    public String getBooksInfo(Model model, @PathVariable(value = "id") Long id, @PathVariable(value = "bookName") String bookName) {
        AuthorDto authorDto = authorService.findByIdAndBookName(id, bookName);
        model.addAttribute("dto", authorDto);
        return "productshow";
    }

    //добавить автора к книге
    @GetMapping("/add-author-for-book")
    public String getAddAuthor(@RequestParam(value = "authorName") String authorName, @RequestParam(value = "booksName") String bookName,
                               Model model) {
        ExceptionDto errorDto = null;
        try {
            bookService.addAuthorForBook(authorName, bookName);
        } catch (AuthorAddException e) {
            errorDto = new ExceptionDto(e.getMessage());
        }
        List<BookDto> bookDtos = bookService.getAllBook();
        model.addAttribute("bookDtos", bookDtos);
        model.addAttribute("error", errorDto);
        return "authors";
    }
}
