package com.example.myapiwithh2.pricecalculate.infrastructure.in;


import java.time.LocalDateTime;

public record PriceDTO(Integer productId, Integer brandId, LocalDateTime appDate) {

}