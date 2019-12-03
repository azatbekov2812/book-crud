package com.example.crudForJob.model.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books;
}
