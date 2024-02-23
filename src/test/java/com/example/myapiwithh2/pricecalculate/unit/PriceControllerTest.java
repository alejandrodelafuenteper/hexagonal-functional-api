package com.example.myapiwithh2.pricecalculate.unit;

import com.example.myapiwithh2.pricecalculate.application.PriceService;
import com.example.myapiwithh2.pricecalculate.infrastructure.in.PriceController;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Test
    void getPrice_ProductIdNull_ThrowsMissingServletRequestParameterException() {
        // Arrange
        Integer productId = null;
        Integer brandId = 1;
        LocalDateTime appDate = LocalDateTime.now();

        // Act & Assert
        assertThrows(MissingServletRequestParameterException.class,
                () -> priceController.getPrice(productId, brandId, appDate));
    }

    @Test
    void getPrice_ProductIdExceedsFiveDigits_ThrowsConstraintViolationException() {
        // Arrange
        Integer productId = 123456;
        Integer brandId = 1;
        LocalDateTime appDate = LocalDateTime.now();

        // Act & Assert
        assertThrows(ConstraintViolationException.class,
                () -> priceController.getPrice(productId, brandId, appDate));
    }
}