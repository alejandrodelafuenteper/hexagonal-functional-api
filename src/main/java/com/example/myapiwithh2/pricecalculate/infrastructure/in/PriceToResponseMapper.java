package com.example.myapiwithh2.pricecalculate.infrastructure.in;

import com.example.myapiwithh2.pricecalculate.domain.Constants;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceResponse;

public class PriceToResponseMapper {
    public PriceResponse map(Price price) {
        return new PriceResponse(price.getProductId(), price.getBrandId(),price.getPriceList(),
                price.getStartDate(), price.getEndDate(), price.getPrice() + Constants.SPACE + price.getCurrency());
    }
}
