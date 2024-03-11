package com.example.demo.user.mapper;


import com.example.demo.user.model.dto.UserNewDto;
import com.example.demo.user.model.dto.UserShortDto;
import com.example.demo.user.model.entity.User;

public class UserMapper {

    public static User toUser(UserNewDto newUser) {

        return new User(
                null,
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword());
    }

    public static UserShortDto toUserShortDto(User user) {

        return new UserShortDto(
                user.getUsername(),
                user.getEmail());
    }
}
