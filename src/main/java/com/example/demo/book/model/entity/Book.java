package com.example.demo.book.model.entity;

import com.example.demo.book.model.entity.links.Book2Author;
import com.example.demo.book.model.entity.links.Book2Genre;
import com.example.demo.user.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Table(name = "books")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_bestseller")
    private Boolean isBestseller;

    @Column(name = "pub_date")
    private LocalDate pubDate;

    private String imageURI;

    @Column(name = "price_old")
    private Integer priceOld;

    @Column(name = "price-current")
    private Integer priceCurrent;

    @ManyToOne
    private User user;

    @OneToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Book2Genre> genreList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    @JsonIgnore
    private List<Book2Author> authorsList = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
