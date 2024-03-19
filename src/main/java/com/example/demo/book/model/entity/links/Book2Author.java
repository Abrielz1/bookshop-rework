package com.example.demo.book.model.entity.links;

import com.example.demo.book.model.entity.Author;
import com.example.demo.book.model.entity.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "book2author")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book2Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", columnDefinition = "INT NOT NULL")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", columnDefinition = "INT NOT NULL")
    private Author author;

    @Column(name = "sort_index", columnDefinition = "INT NOT NULL  DEFAULT 0")
    private int sortIndex;
}
