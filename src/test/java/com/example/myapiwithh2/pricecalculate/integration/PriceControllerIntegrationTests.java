package com.example.myapiwithh2.pricecalculate.integration;

import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test1() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        MvcResult mvcResult = test("/prices/",35455, 1, localDateTime);

        String responseBody = mvcResult.getResponse().getContentAsString();
        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertNotNull(mvcResult);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(priceResponse);
        assertEquals("35.50 EUR", priceResponse.price());

    }

    private MvcResult test(String url, Integer productId, Integer brandId, LocalDateTime appDate) throws Exception {
        return mockMvc.perform(get(url)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .param("appDate", appDate.toString()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void test2() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        MvcResult mvcResult = test("/prices/",35455, 1, localDateTime);

        String responseBody = mvcResult.getResponse().getContentAsString();
        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertNotNull(mvcResult);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(priceResponse);
        assertEquals("25.45 EUR", priceResponse.price());
    }

    @Test
    public void test3() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        MvcResult mvcResult = test("/prices/",35455, 1, localDateTime);
        String responseBody = mvcResult.getResponse().getContentAsString();
        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertNotNull(mvcResult);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(priceResponse);
        assertEquals("35.50 EUR", priceResponse.price());
    }

    @Test
    public void test4() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        MvcResult mvcResult = test("/prices/",35455, 1, localDateTime);

        String responseBody = mvcResult.getResponse().getContentAsString();
        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertNotNull(mvcResult);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(priceResponse);
        assertEquals("30.50 EUR", priceResponse.price());
    }

    @Test
    public void test5() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        MvcResult mvcResult = test("/prices/",35455, 1, localDateTime);

        String responseBody = mvcResult.getResponse().getContentAsString();
        PriceResponse priceResponse = objectMapper.readValue(responseBody, PriceResponse.class);

        assertNotNull(mvcResult);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(priceResponse);
        assertEquals("38.95 EUR", priceResponse.price());
    }

    @Test
    public void testNotFoundPrice() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        MvcResult mvcResult = test("/prices/",35456, 1, localDateTime);

        assertNotNull(mvcResult);
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());

    }
    @Test
    public void testBadUrl() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        MvcResult mvcResult = mockMvc.perform(get("/prices2/")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("appDate", localDateTime.toString()))
                .andExpect(status().isNotFound())
                .andReturn();

        assertNotNull(mvcResult);
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }
}
