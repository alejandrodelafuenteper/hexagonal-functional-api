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

    @Autowired
    private PriceRepository priceRepository;

    public Optional<Price> findPrice(Price price) throws NotPriceFoundException {

        List<Price> prices = priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                price.getProductId(), price.getBrandId(), price.getStartDate(), price.getEndDate());

        if(prices.isEmpty()){
            throw new NotPriceFoundException("No price was found for the provided parameters.");
        }
        return prices.stream().max(Comparator.comparingInt(Price::getPriority));

    }
}
