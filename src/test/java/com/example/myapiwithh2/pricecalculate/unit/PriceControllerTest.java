package com.example.myapiwithh2.pricecalculate.unit;

import com.example.myapiwithh2.pricecalculate.application.PriceService;
import com.example.myapiwithh2.pricecalculate.domain.NotPriceFoundException;
import com.example.myapiwithh2.pricecalculate.infrastructure.in.PriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
public class PriceControllerTest {

    private PriceController priceController;

    @BeforeEach
    void setUp() {
        PriceService priceServiceMock = mock(PriceService.class);
        priceController = new PriceController(priceServiceMock);
    }

    @Test
    void testGetPrice_NullProductId() throws NotPriceFoundException {
        // Arrange
        LocalDateTime appDate = LocalDateTime.now();
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("Product ID cannot be null", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<?> responseEntity = priceController.getPrice(null, 1, appDate);

        // Assert
        assertEquals(expectedResponse, responseEntity);
    }

    @Test
    void testGetPrice_BrandIdExceedsLimit() throws NotPriceFoundException {
        // Arrange
        LocalDateTime appDate = LocalDateTime.now();
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("Brand ID cannot have more than 5 digits", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<?> responseEntity = priceController.getPrice(123456, 1, appDate);

        // Assert
        assertEquals(expectedResponse, responseEntity);
    }
}