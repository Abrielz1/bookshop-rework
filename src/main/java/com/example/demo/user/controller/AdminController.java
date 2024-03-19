package com.example.demo.user.controller;

import com.example.demo.user.model.dto.UserFullDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public List<UserFullDto> getListOfUsersWithFullInfo() {

        return null;
    }
}
