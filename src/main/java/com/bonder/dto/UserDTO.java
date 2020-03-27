package com.bonder.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;
}
