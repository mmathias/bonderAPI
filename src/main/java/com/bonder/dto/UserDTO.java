package com.bonder.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String email;
    private String name;
    private String password;
    private LocalDate joinDate;
}
