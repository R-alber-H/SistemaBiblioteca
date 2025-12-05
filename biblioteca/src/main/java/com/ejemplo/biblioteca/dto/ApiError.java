package com.ejemplo.biblioteca.dto;

public record ApiError(
        int responseCode,
        String responseMessage
) {
}
