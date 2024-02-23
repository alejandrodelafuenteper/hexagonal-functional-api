package com.example.myapiwithh2.pricecalculate.infrastructure.in;

import com.example.myapiwithh2.pricecalculate.application.PriceService;
import com.example.myapiwithh2.pricecalculate.domain.NotPriceFoundException;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@Validated
public class PriceController {

    private final PriceService priceService;
    private final PriceDTOToDomainMapper toDomainMapper;
    private final PriceToResponseMapper toResponseMapper;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
        this.toDomainMapper = new PriceDTOToDomainMapper();
        this.toResponseMapper = new PriceToResponseMapper();
    }

    @GetMapping("/")
    public ResponseEntity<?> getPrice(
            @Valid @NotNull(message = "Product ID cannot be null")
            @Max(value = 99999, message = "Brand ID cannot have more than 5 digits")
            @RequestParam Integer productId,

            @Valid @NotNull(message = "Brand ID cannot be null")
            @RequestParam Integer brandId,

            @Valid @NotNull(message = "Application date cannot be null")
            @RequestParam LocalDateTime appDate)
            throws NotPriceFoundException {

        PriceDTO priceDTO = new PriceDTO(productId, brandId, appDate);
        Price price = priceService.findPrice(toDomainMapper.map(priceDTO));
        return ResponseEntity.ok(toResponseMapper.map(price));
    }
}
