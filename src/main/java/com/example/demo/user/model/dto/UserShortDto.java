package com.example.demo.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {

    private String displayName;

    private String email;
}



//  private LocalDateTime createdAt;

//  private LocalDateTime updatedAt;