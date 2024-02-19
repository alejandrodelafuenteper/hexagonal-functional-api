package com.example.myapiwithh2.pricecalculate.infrastructure.in;

import com.example.myapiwithh2.pricecalculate.application.PriceService;
import com.example.myapiwithh2.pricecalculate.domain.NotPriceFoundException;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceResponse;
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
                                                  @RequestParam LocalDateTime appDate) {

        PriceDTO priceDTO = new PriceDTO(productId, brandId, appDate);
        try {
            Optional<Price> price = priceService.findPrice(toDomainMapper.map(priceDTO));
            PriceResponse priceResponse = toResponseMapper.map(price.get());
            return ResponseEntity.ok(priceResponse);
        } catch (NotPriceFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


        /*
        return price.map(p ->
                ResponseEntity.ok(toResponseMapper.map(p))
        ).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún precio para los parámetros proporcionados.")
        );
        */
    }
}
