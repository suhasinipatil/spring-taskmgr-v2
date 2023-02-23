package com.example.springtaskmgrv2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
