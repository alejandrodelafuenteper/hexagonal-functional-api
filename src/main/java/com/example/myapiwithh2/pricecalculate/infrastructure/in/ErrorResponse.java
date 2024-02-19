package com.example.myapiwithh2.pricecalculate.infrastructure.in;

public class ErrorResponse {
    private String code;
    private String description;

    public ErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}