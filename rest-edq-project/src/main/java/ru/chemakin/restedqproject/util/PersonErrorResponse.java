package ru.chemakin.restedqproject.util;

import lombok.Data;

@Data
public class PersonErrorResponse {
    private String message;
    private long timestamp;
}
