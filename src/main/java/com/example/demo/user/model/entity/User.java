package com.example.demo.user.model.entity;

import com.example.demo.book.model.entity.Author;
import com.example.demo.book.model.entity.Book;
import com.example.demo.book.model.entity.Genre;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "users")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "TEXT")
    private String hash;

    @Column(unique = true, name = "username")
    private String username;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(columnDefinition = "TEXT", name = "display_name")
    private String displayName;

    private Long registrationTime;

    private Long updateTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private List<Book> uploadedBooksList = new ArrayList<>(); // list of uploaded books by current user

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private List<Author> createdAuthorsList = new ArrayList<>(); // list of uploaded books by current user

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private List<Genre> createdGenresList = new ArrayList<>(); // list of uploaded genres by current user
    // todo: добавть роли и связи с коментами и книгами
}
