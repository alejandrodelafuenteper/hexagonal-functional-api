package com.example.myapiwithh2.pricecalculate.infrastructure.in;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class PriceDTO {

    @Valid
    @NotNull(message = "Product ID cannot be null")
    private Integer productId;

    @Valid
    @NotNull(message = "Brand ID cannot be null")
    private Integer brandId;

    @Valid
    @NotNull(message = "Application date cannot be null")
    private LocalDateTime appDate;


    public PriceDTO(Integer productId, Integer brandId, LocalDateTime appDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.appDate = appDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public LocalDateTime getAppDate() {
        return appDate;
    }
}