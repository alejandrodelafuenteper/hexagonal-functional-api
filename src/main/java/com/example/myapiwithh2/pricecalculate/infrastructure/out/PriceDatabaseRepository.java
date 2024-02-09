package com.example.myapiwithh2.pricecalculate.infrastructure.out;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface PriceDatabaseRepository extends JpaRepository<PriceEntity, Integer> {

    List<PriceEntity> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer productId, Integer brandId, Date appDateAfter, Date appDateBefore);
}