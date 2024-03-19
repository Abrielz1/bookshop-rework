package com.example.demo.book.model.entity;

import com.example.demo.user.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "genres")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "INT")
    private Long parentId;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @ManyToOne
    private User user;
}
