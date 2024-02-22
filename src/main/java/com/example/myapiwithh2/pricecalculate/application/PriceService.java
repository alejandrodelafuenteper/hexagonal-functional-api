package com.example.myapiwithh2.pricecalculate.application;


import com.example.myapiwithh2.pricecalculate.domain.NotPriceFoundException;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.domain.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price findPrice(Price price) throws NotPriceFoundException {

        List<Price> prices = priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                price.getProductId(), price.getBrandId(), price.getStartDate(), price.getEndDate());

        return prices.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElseThrow(() -> new NotPriceFoundException("No price was found for the provided parameters."));
    }
}
