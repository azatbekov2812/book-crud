package com.example.crudForJob.service.impl;

import com.example.crudForJob.model.Dto.ADto;
import com.example.crudForJob.model.Dto.AuthorDto;
import com.example.crudForJob.model.Dto.BookDto;
import com.example.crudForJob.model.Entity.Author;
import com.example.crudForJob.model.Entity.Book;
import com.example.crudForJob.model.Entity.Publisher;
import com.example.crudForJob.repository.AuthorRepo;
import com.example.crudForJob.service.AuthorService;
import com.example.crudForJob.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookService bookService;

    @Override
    public List<ADto> getAll() {
        List<Author> authorList = authorRepo.findAll();
        List<ADto> aDtos = new ArrayList<>();
        for (Author author : authorList){
            ADto dto = new ADto();
            dto.setAuthorName(author.getAuthorName());
            aDtos.add(dto);
        }
        return aDtos;
    }

    @Override
    public Author findByAuthorName(String name) {
        return authorRepo.findByAuthorName(name);
    }



    @Override
    public Author saveAuthor(String authorName) throws Exception{
        Author author = new Author();
        Author entity = authorRepo.findByAuthorName(authorName);
        if (entity != null){
            throw new Exception("автор с таким именем уже существует");
        }
        author.setAuthorName(authorName);
        return authorRepo.save(author);
    }

    @Override
    public AuthorDto findByIdAndBookName(Long id, String bookName) {
        Optional<Author> author = authorRepo.findById(id);
        Book book = bookService.finByBookName(bookName);
        AuthorDto dto = new AuthorDto();
        BookDto bookDto = new BookDto();
        if (author.isPresent()){
            Author entity = author.get();
            dto.setId(entity.getId());
            dto.setAuthorName(entity.getAuthorName());
            bookDto.setId(book.getId());
            bookDto.setBookName(book.getBookName());
            bookDto.setPublisherName(book.getPublisher().getPublisherName());
            bookDto.setPrice(book.getPrice());
            bookDto.setDescription(book.getDescription());
            dto.setBookDto(bookDto);
        }
        return dto;
    }
}
