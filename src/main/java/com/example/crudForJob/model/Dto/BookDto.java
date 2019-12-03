package com.example.crudForJob.model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String bookName;
    private String description;
    private BigDecimal price;
    private String publisherName;
    private List<AuthorDto2> authorDto;


}
