package com.example.myapiwithh2.pricecalculate.infrastructure.in;

import com.example.myapiwithh2.pricecalculate.application.PriceService;
import com.example.myapiwithh2.pricecalculate.domain.Price;
import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<PriceResponse> getPrice( @RequestParam Integer productId,
                                                   @RequestParam Integer brandId,
                                                   @RequestParam LocalDateTime appDate){

        PriceDTO priceDTO = new PriceDTO(productId, brandId, appDate);

        Optional<Price> price = priceService.findPrice(toDomainMapper.map(priceDTO));

        return price
                .map(toResponseMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        return errors;
    }

}
