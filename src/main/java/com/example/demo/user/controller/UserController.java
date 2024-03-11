package com.example.demo.user.controller;

import com.example.demo.common.Create;
import com.example.demo.common.Update;
import com.example.demo.user.model.dto.UserNewDto;
import com.example.demo.user.model.dto.UserShortDto;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserShortDto> sendAllUsersAccountsList(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                       @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("\nList of user were sent via users controller at time: " + LocalDateTime.now() + "\n");
        PageRequest page = PageRequest.of(from / size, size);
        return userService.getAllUsersAccountsList(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserShortDto sendUserAccountById(@Positive @PathVariable(name = "id") Long userId) {

        log.info("\nUser account with id: %d was sent via users controller at time: ".formatted(userId)
                + LocalDateTime.now() + "\n");
        return userService.getUserAccountById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserShortDto registerUserAccount(@Validated(Create.class) @RequestBody UserNewDto newUser) {

        log.info("\nUser account was created via users controller at time: " + LocalDateTime.now() + "\n");
        return userService.registerUserAccount(newUser);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserShortDto updateUserAccount(@Positive @PathVariable(name = "id") Long userId,
                                          @Validated(Update.class) @RequestBody UserNewDto updateUser) {

        log.info("\nUser account with id: %d was updated via users controller at time: ".formatted(userId)
                + LocalDateTime.now() + "\n");
        return userService.updateUserAccount(userId, updateUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserShortDto deleteUserAccount(@Positive @PathVariable(name = "id") Long userId) {

        log.info("\nUser account with id: %d was deleted via users controller at time: ".formatted(userId)
                + LocalDateTime.now() + "\n");
        return userService.removeUserAccountById(userId);
    }
}
