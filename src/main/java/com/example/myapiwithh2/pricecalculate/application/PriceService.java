package com.example.myapiwithh2.pricecalculate.application;


import com.example.myapiwithh2.pricecalculate.domain.Constants;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.domain.PriceRepository;
import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceEntityToDomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;
    
    public Optional<Price> findPrice(Price price) {

        List<Price> prices = priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                price.getProductId(), price.getBrandId(),price.getStartDate(),price.getEndDate());

        Optional<Price> optionalPrice = prices.stream()
                .filter(p -> p.getPriority() == Constants.PRIORITY)
                .findFirst();

        if (optionalPrice.isPresent()) {
            return Optional.of(optionalPrice.get());
        } else {
            return Optional.ofNullable(prices.stream().findFirst().orElse(null));
        }
    }
}
