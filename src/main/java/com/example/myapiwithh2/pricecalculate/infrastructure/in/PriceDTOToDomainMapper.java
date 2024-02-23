package com.example.myapiwithh2.pricecalculate.infrastructure.in;

import com.example.myapiwithh2.pricecalculate.domain.Price;

public class PriceDTOToDomainMapper {

    public Price map(PriceDTO priceDTO) {
        Price price = new Price();
        price.setProductId(priceDTO.productId());
        price.setBrandId(priceDTO.brandId());
        price.setStartDate(java.sql.Timestamp.valueOf(priceDTO.appDate()));
        price.setEndDate(java.sql.Timestamp.valueOf(priceDTO.appDate()));
        return price;
    }
}
