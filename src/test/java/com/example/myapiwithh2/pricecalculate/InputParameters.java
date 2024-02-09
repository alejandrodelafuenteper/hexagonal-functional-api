package com.example.myapiwithh2.pricecalculate;

import java.time.LocalDateTime;
import java.util.Date;

public class InputParameters {
    private Integer productId;
    private Integer brandId;
    private LocalDateTime appDate;

    public InputParameters(Integer productId, Integer brandId, LocalDateTime appDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.appDate = appDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getAppDate() {
        return appDate;
    }

    public void setAppDate(LocalDateTime appDate) {
        this.appDate = appDate;
    }
}
