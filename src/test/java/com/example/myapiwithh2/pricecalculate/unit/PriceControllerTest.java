package com.example.myapiwithh2.pricecalculate.unit;

import com.example.myapiwithh2.pricecalculate.application.PriceService;
import com.example.myapiwithh2.pricecalculate.domain.NotPriceFoundException;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.infrastructure.in.PriceController;
import com.example.myapiwithh2.pricecalculate.infrastructure.in.PriceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    private PriceController priceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        priceController = new PriceController(priceService);
    }

    @Test
    public void testGetPrice_PriceFound() throws NotPriceFoundException {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        PriceDTO priceDTO = new PriceDTO(35455, 1, localDateTime);
        Price price = new Price(1, 1, java.sql.Timestamp.valueOf(localDateTime), java.sql.Timestamp.valueOf(localDateTime.plusDays(1)),
                1, 1, 1,new BigDecimal(1),"EUR",java.sql.Timestamp.valueOf(localDateTime),"James");
        Optional<Price> optionalPrice = Optional.of(price);

        when(priceService.findPrice(any())).thenReturn(optionalPrice);

        // Act
        ResponseEntity<?> responseEntity = priceController.getPrice(priceDTO.getProductId(), priceDTO.getBrandId(), priceDTO.getAppDate());

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testGetPrice_PriceNotFound() throws NotPriceFoundException {
        // Arrange
        PriceDTO priceDTO = new PriceDTO(1, 1, LocalDateTime.now());
        when(priceService.findPrice(any())).thenThrow(NotPriceFoundException.class);

        // Act
        ResponseEntity<?> responseEntity = priceController.getPrice(priceDTO.getProductId(), priceDTO.getBrandId(), priceDTO.getAppDate());

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        // Add more assertions if needed
    }
}