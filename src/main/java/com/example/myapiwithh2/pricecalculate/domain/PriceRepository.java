package com.example.myapiwithh2.pricecalculate.domain;

import java.util.Date;
import java.util.List;

public interface PriceRepository {

    List<Price> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer productId, Integer brandId, Date appDateAfter, Date appDateBefore);

}