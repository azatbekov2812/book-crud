package com.example.crudForJob.service;

import com.example.crudForJob.Exception.AuthorAddException;
import com.example.crudForJob.model.Dto.BDto;
import com.example.crudForJob.model.Dto.BookDto;
import com.example.crudForJob.model.Entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    Book saveBook(String bookName, String publishName, BigDecimal price, String description, String authorName) throws Exception;
    Book finByBookName(String bookName);
    Book addAuthorForBook(String authorName, String bookName) throws AuthorAddException;
    List<BookDto> getAllBook();
    List<BookDto> getTestAllBook(String bookName, String authorName);
    BookDto findDtoById(Long id);
    List<BDto> getAll();
}
