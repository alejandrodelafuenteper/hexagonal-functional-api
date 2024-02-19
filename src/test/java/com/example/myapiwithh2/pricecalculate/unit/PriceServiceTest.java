package com.example.myapiwithh2.pricecalculate.unit;


import com.example.myapiwithh2.pricecalculate.application.PriceService;
import com.example.myapiwithh2.pricecalculate.domain.NotPriceFoundException;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.domain.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
    @InjectMocks
    private PriceService priceService;
    @Mock
    private PriceRepository priceRepository;


    @Test
    public void testFindPrice_PriceFound() throws NotPriceFoundException {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Price price = new Price(1, 1, java.sql.Timestamp.valueOf(localDateTime), java.sql.Timestamp.valueOf(localDateTime.plusDays(1)),
                1, 1, 1,new BigDecimal(1),"EUR",java.sql.Timestamp.valueOf(localDateTime),"James");
        List<Price> prices = new ArrayList<>();
        prices.add(price);

        when(priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyInt(), anyInt(), any(), any())).thenReturn(prices);

        // Act
        Optional<Price> result = priceService.findPrice(price);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(price, result.get());
    }

    @Test
    public void testFindPrice_PriceNotFound() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Price price = new Price(1, 1, java.sql.Timestamp.valueOf(localDateTime), java.sql.Timestamp.valueOf(localDateTime.plusDays(1)),
                1, 1, 1,new BigDecimal(1),"EUR",java.sql.Timestamp.valueOf(localDateTime),"James");
        when(priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(), any(), any(), any())).thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(NotPriceFoundException.class, () -> {
            priceService.findPrice(price);
        });
    }
}