package com.example.demo.book.model.entity.links;

import com.example.demo.book.model.entity.Book;
import com.example.demo.book.model.entity.Genre;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "book2genre")
public class Book2Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", columnDefinition = "INT NOT NULL")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id", columnDefinition = "INT NOT NULL")
    private Genre genre;

    @Column(name = "sort_index", columnDefinition = "INT NOT NULL  DEFAULT 0")
    private int sortIndex;
}
