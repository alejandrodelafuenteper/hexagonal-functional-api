package com.example.myapiwithh2.pricecalculate.unit;

import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceDatabaseRepository;
import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceEntity;
import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PriceRepositoryAdapterTest {

    private PriceRepositoryAdapter priceRepositoryAdapter;
    @Mock
    private PriceDatabaseRepository priceDatabaseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        priceRepositoryAdapter = new PriceRepositoryAdapter(priceDatabaseRepository);
    }

    @Test
    void testFindAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        List<PriceEntity> mockEntities = List.of(
                new PriceEntity(1, 1, java.sql.Timestamp.valueOf(localDateTime), java.sql.Timestamp.valueOf(localDateTime.plusDays(1)),
                        1, 1, 1,new BigDecimal(1),"EUR",java.sql.Timestamp.valueOf(localDateTime),"James")
        );
        List<Price> expectedPrices = List.of(
                new Price(1, 1, java.sql.Timestamp.valueOf(localDateTime), java.sql.Timestamp.valueOf(localDateTime.plusDays(1)),
                        1, 1, 1,new BigDecimal(1),"EUR",java.sql.Timestamp.valueOf(localDateTime),"James")
        );
        when(priceDatabaseRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(any(), any(), any(), any()))
                .thenReturn(mockEntities);

        // Act
        List<Price> actualPrices = priceRepositoryAdapter.
                findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1,
                        java.sql.Timestamp.valueOf(localDateTime), java.sql.Timestamp.valueOf(localDateTime.plusDays(1)));

        // Assert
        assertEquals(expectedPrices, actualPrices);
    }
}
