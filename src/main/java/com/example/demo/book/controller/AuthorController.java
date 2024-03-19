package com.example.demo.book.controller;

import com.example.demo.book.model.dto.AuthorDto;
import com.example.demo.book.model.dto.AuthorNewDto;
import com.example.demo.book.service.AuthorService;
import com.example.demo.common.Create;
import com.example.demo.common.Update;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDto> sendAllUsersAccountsList(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("\nList of authors were sent via authors controller at time: " + LocalDateTime.now() + "\n");
        PageRequest page = PageRequest.of(from / size, size);

        return authorService.getAuthorsList(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDto  sendUserAccountById(@Positive @PathVariable(name = "id") Long authorId,
                                          @Positive @RequestParam() Long userId) {

        log.info(("\nAuthor with id: %d" +
                " was sent via authors controller" +
                " by user with id: %d at time: ").formatted(authorId, userId)
                + LocalDateTime.now() + "\n");
        return authorService.getAuthorById(authorId, userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto registerAuthor(@Positive @RequestParam() Long userId,
                                    @Validated(Create.class) @RequestBody AuthorNewDto newAuthor) {

        log.info(("\nAuthor with" +
                " was created via authors controller" +
                " by user with id: %d at time: ").formatted(userId)
                + LocalDateTime.now() + "\n");
        return authorService.createAuthor(userId, newAuthor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDto updateAuthor(@Positive @PathVariable(name = "id") Long authorId,
                                  @Positive @RequestParam() Long userId,
                                  @Validated(Update.class) @RequestBody AuthorNewDto updateAuthor) {

        log.info(("\nAuthor with id: %d" +
                " was updated via authors controller" +
                " by user with id: %d at time: ").formatted(authorId, userId)
                + LocalDateTime.now() + "\n");
        return authorService.updateAuthorByUserIdAndAuthorId(authorId, userId, updateAuthor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AuthorDto deleteAuthor(@Positive @PathVariable(name = "id") Long authorId,
                                  @Positive @RequestParam() Long userId) {

        log.info(("\nAuthor with id: %d" +
                " was deleted via authors controller" +
                " by user with id: %d at time: ").formatted(authorId, userId)
                + LocalDateTime.now() + "\n");
        return authorService.deleteByAuthorIdAndUserId(authorId, userId);
    }
}
