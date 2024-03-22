package ru.innopolis.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {
    @Size(min=4, max=50)
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @NotEmpty
    private String password;
}
