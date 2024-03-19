package com.example.demo.user.controller;

import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.dto.UserFullDto;
import com.example.demo.user.model.dto.UserNewDto;
import com.example.demo.user.model.dto.UserShortDto;
import com.example.demo.user.model.entity.User;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.example.demo.user.mapper.UserMapper.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    private UserNewDto userNewDto;

    private UserShortDto userShortDto;

    private UserFullDto userFullDto;

    @BeforeEach
    void beforeEach() {

        userNewDto = UserNewDto.builder()
                .username("User1 name")
                .email("user1@mail.com")
                .password("")
                .displayName("")
                .build();

        user = toUser(userNewDto);
        user.setId(1L);

        userShortDto = toUserShortDto(user);
        userFullDto = toFullDto(user);
    }

    @Test
    void sendAllUsersAccountsList() {
    }

    @Test
    void sendUserAccountById() {
    }

    @Test
    void registerUserAccount() {
    }

    @Test
    void updateUserAccount() {
    }

    @Test
    void deleteUserAccount() {
    }
}