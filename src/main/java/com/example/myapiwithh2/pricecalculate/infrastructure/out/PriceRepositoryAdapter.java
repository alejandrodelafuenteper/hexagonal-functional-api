package com.example.myapiwithh2.pricecalculate.infrastructure.out;

import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.domain.PriceRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceDatabaseRepository priceDatabaseRepository;
    private final PriceEntityToDomainMapper toDomainMapper;

    public PriceRepositoryAdapter(PriceDatabaseRepository priceDatabaseRepository) {
        this.priceDatabaseRepository = priceDatabaseRepository;
        this.toDomainMapper = new PriceEntityToDomainMapper();
    }

    @Override
    public List<Price> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer productId, Integer brandId, Date appDateAfter, Date appDateBefore) {
        List<PriceEntity> prices = priceDatabaseRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, appDateAfter, appDateBefore);
        return toDomainMapper.map(prices);
    }
}
