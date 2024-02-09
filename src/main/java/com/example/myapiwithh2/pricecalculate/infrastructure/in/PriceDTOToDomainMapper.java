package com.example.myapiwithh2.pricecalculate.infrastructure.in;

import com.example.myapiwithh2.pricecalculate.domain.Price;

public class PriceDTOToDomainMapper {

    public Price map(PriceDTO priceDTO) {
        Price price = new Price();
        price.setProductId(priceDTO.getProductId());
        price.setBrandId(priceDTO.getBrandId());
        price.setStartDate(java.sql.Timestamp.valueOf(priceDTO.getAppDate()));
        price.setEndDate(java.sql.Timestamp.valueOf(priceDTO.getAppDate()));
        return price;
    }
}
