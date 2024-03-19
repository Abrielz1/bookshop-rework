package com.example.demo.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;

    private String firstName;

    private String patronym;

    private String lastName;

    private String biography;

    private Long creatorId;

   // private List<BookDto> listOfWroteBooks = new ArrayList<>();
}
