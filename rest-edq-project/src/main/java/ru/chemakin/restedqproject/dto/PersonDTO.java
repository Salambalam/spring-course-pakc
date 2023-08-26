package ru.chemakin.restedqproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDTO {

    @NotEmpty(message = "Name should not be empty.")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters.")
    private String username;

    @Min(value = 0, message = "age should be grater then 0.")
    private int age;

}
