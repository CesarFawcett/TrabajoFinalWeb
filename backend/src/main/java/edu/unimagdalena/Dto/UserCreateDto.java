package edu.unimagdalena.Dto;

import lombok.*;

@Data
public class UserCreateDto {
    private int id;
    private String fullname;
    private String username;
    private String password;
}
