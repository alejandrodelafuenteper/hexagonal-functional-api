package com.example.myapiwithh2.pricecalculate.infrastructure.in;

import com.example.myapiwithh2.pricecalculate.application.PriceService;
import com.example.myapiwithh2.pricecalculate.domain.NotPriceFoundException;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

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
    public ResponseEntity<?> getPrice(@RequestParam Integer productId,
                                                  @RequestParam Integer brandId,
                                                  @RequestParam LocalDateTime appDate) throws NotPriceFoundException {

        PriceDTO priceDTO = new PriceDTO(productId, brandId, appDate);
            Optional<Price> price = priceService.findPrice(toDomainMapper.map(priceDTO));
            return ResponseEntity.ok(toResponseMapper.map(price.get()));
    }
}
