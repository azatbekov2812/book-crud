package com.example.crudForJob.service.impl;

import com.example.crudForJob.Exception.AuthorAddException;
import com.example.crudForJob.Exception.BookException;
import com.example.crudForJob.model.Dto.AuthorDto2;
import com.example.crudForJob.model.Dto.BDto;
import com.example.crudForJob.model.Dto.BookDto;
import com.example.crudForJob.model.Entity.Author;
import com.example.crudForJob.model.Entity.Book;
import com.example.crudForJob.model.Entity.Publisher;
import com.example.crudForJob.repository.BookRepo;
import com.example.crudForJob.service.AuthorService;
import com.example.crudForJob.service.BookService;
import com.example.crudForJob.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorService authorService;

    @Override
    public Book saveBook(String bookName, String publisherName, BigDecimal price, String description, String authorName) throws Exception {
        Book book = new Book();
        Book entities = bookRepo.findByBookName(bookName);
        if (entities != null){
            throw new BookException("книга с таким названием существует");
        }
        book.setBookName(bookName);
        book.setPrice(price);
        book.setDescription(description);
        Author author = authorService.findByAuthorName(authorName);
        if (author == null){
            throw new Exception("выберите автора");
        }
        List<Author> authorList = new ArrayList<>();
        authorList.add(author);
        book.setAuthors(authorList);
        //------------------------------
        Publisher publisher = publisherService.findByName(publisherName);
        if (publisher == null){
            throw new Exception("выберите издателя");
        }
        book.setPublisher(publisher);
        Book entity = bookRepo.save(book);
        return entity;
    }

    @Override
    public Book finByBookName(String bookName) {
        return bookRepo.findByBookName(bookName);
    }

    @Override
    public Book addAuthorForBook(String authorName, String bookName) throws AuthorAddException {
        Book book = bookRepo.findByBookName(bookName);
        List<Author> authorList = book.getAuthors();
        Author author = authorService.findByAuthorName(authorName);
        if (authorList.contains(author)){
            throw new AuthorAddException("Автор уже есть в данной книге");
        }
        authorList.add(author);
        book.setAuthors(authorList);
        return bookRepo.save(book);
    }


    @Override
    public List<BookDto> getAllBook() {
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> bookList = bookRepo.findAll();
        for (Book book : bookList){
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setBookName(book.getBookName());
            bookDto.setDescription(book.getDescription());
            bookDto.setPrice(book.getPrice());
            Publisher publisher = book.getPublisher();
            bookDto.setPublisherName(publisher.getPublisherName());
            List<Author> authorList = book.getAuthors();
            List<AuthorDto2> authorDto2s = new ArrayList<>();
            for (Author author : authorList){
                AuthorDto2 authorDto2 = new AuthorDto2();
                authorDto2.setId(author.getId());
                authorDto2.setAuthorName(author.getAuthorName());
                authorDto2s.add(authorDto2);
            }
            bookDto.setAuthorDto(authorDto2s);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @Override
    public BookDto findDtoById(Long id) {
        BookDto bookDto = new BookDto();
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()){
            Book entity = book.get();
            bookDto.setBookName(entity.getBookName());
            bookDto.setDescription(entity.getDescription());
            bookDto.setPrice(entity.getPrice());
        }
        return bookDto;
    }

    @Override
    public List<BookDto> getTestAllBook(String bookName, String authorName) {
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> bookList = null;
        if ("All".equals(bookName)){
            bookList = bookRepo.findAll();
        } else {
            Book book = bookRepo.findByBookName(bookName);
            bookList = new ArrayList<>();
            bookList.add(book);
        }
        for (Book book : bookList){
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setBookName(book.getBookName());
            bookDto.setDescription(book.getDescription());
            bookDto.setPrice(book.getPrice());
            Publisher publisher = book.getPublisher();
            bookDto.setPublisherName(publisher.getPublisherName());
            List<Author> authorList = book.getAuthors();
            List<AuthorDto2> authorDto2s = new ArrayList<>();
            for (Author author : authorList){
                AuthorDto2 authorDto2 = new AuthorDto2();
                if ("All".equals(authorName)){
                    authorDto2.setId(author.getId());
                    authorDto2.setAuthorName(author.getAuthorName());
                    authorDto2s.add(authorDto2);
                } else {
                    if (authorName.equals(author.getAuthorName())){
                        authorDto2.setId(author.getId());
                        authorDto2.setAuthorName(author.getAuthorName());
                        authorDto2s.add(authorDto2);
                    }
                }

            }
            bookDto.setAuthorDto(authorDto2s);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @Override
    public List<BDto> getAll() {
        List<Book> bookList = bookRepo.findAll();
        List<BDto> dtos = new ArrayList<>();
        for (Book book : bookList){
            BDto dto = new BDto();
            dto.setBookName(book.getBookName());
            dtos.add(dto);
        }
        return dtos;
    }
}
