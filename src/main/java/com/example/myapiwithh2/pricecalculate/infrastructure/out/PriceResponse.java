package com.example.myapiwithh2.pricecalculate.infrastructure.out;

import java.util.Date;

public record PriceResponse(Integer productId, Integer brandId, Integer priceList, Date startDate, Date endDate, String price){

}