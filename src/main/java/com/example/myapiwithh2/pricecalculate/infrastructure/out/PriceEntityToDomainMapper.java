package com.example.myapiwithh2.pricecalculate.infrastructure.out;

import com.example.myapiwithh2.pricecalculate.domain.Price;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceEntityToDomainMapper {

    public List<Price> map(List<PriceEntity> pricesEntity) {

        List<Price> prices = pricesEntity.stream()
                .map(entity -> new Price(
                        entity.getId(),
                        entity.getBrandId(),
                        entity.getStartDate(),
                        entity.getEndDate(),
                        entity.getPriceList(),
                        entity.getProductId(),
                        entity.getPriority(),
                        entity.getPrice(),
                        entity.getCurrency(),
                        entity.getLastUpdate(),
                        entity.getLastUpdateBy()
                ))
                .collect(Collectors.toList());
        return prices;
    }
}
