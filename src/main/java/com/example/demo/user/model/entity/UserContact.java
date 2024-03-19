package com.example.demo.user.model.entity;

import com.example.demo.user.model.entity.enums.ContactsType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Table(name = "users_contact")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id; //идентификатор пользователя, к которому относится данный контакт

    @Enumerated(EnumType.STRING)
    private ContactsType type;

    private Short approved; //подтверждён ли контакт (0 или 1)

    @Column(columnDefinition = "TEXT")
    private String code; //код подтверждения

    private Integer code_trials;

    private LocalDateTime code_time; // дата и время формирования кода подтверждения

    @Column(columnDefinition = "TEXT")
    private String contact; //контакт (e-mail или телефон)
}
