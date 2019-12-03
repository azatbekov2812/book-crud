package com.example.crudForJob.model.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "published_at")
    private LocalDate publishedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_book",
        joinColumns = @JoinColumn(name ="book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private List<Author> authors;


}
