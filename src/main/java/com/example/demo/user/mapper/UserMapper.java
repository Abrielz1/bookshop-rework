package com.example.demo.user.mapper;

import com.example.demo.user.model.dto.UserFullDto;
import com.example.demo.user.model.dto.UserNewDto;
import com.example.demo.user.model.dto.UserShortDto;
import com.example.demo.user.model.entity.User;
import java.util.ArrayList;
import java.util.UUID;

public class UserMapper {

    public static User toUser(UserNewDto newUser) {

        return new User(
                null,
                UUID.randomUUID().toString(),
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getDisplayName(),
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

    }

    public static UserShortDto toUserShortDto(User user) {

        return new UserShortDto(
                user.getDisplayName(),
                user.getEmail()
        );
    }

    public static UserFullDto toFullDto(User user) {

        return new UserFullDto(
                user.getId(),
                user.getHash(),
                user.getUsername(),
                user.getEmail(),
                user.getDisplayName(),
                user.getRegistrationTime(),
                user.getUpdateTime(),
                user.getUploadedBooksList(),
                user.getCreatedAuthorsList(),
                user.getCreatedGenresList()
        );
    }
}
