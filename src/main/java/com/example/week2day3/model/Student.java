package com.example.week2day3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;


@AllArgsConstructor @Data
public class Student {
    @NotNull(message = "ID cannot be null")
    @PositiveOrZero(message = "ID should be ID>=0")
    private Integer id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, message = "Name should at least have 3 characters")
    private String name;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email")
    private String email;
    @NotNull(message = "Password cannot be null")
    @Size(min = 5,message = "Password should at least be 5 characters")
    private String password;
    @NotNull(message = "Age cannot be null")
    @Min(value=18,message = "Age should at least be 18")
    @Positive(message = "Age cannot be negative")
    private Integer age;
    @NotNull(message = "Gender cannot be null")
    @Pattern(regexp = "[\t^(?:m|f)$]",message = "Please enter gender as : m for Male / f for Female")
    private String gender;
}
