package com.example.myapiwithh2.pricecalculate.integration;

import com.example.myapiwithh2.pricecalculate.infrastructure.out.PriceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
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
class PriceEntityCalculateApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test1() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        PriceResponse priceResponse = test("/prices/",35455, 1, localDateTime);

        assertNotNull(priceResponse);
        assertEquals("35.50 EUR", priceResponse.price());

    }

    private PriceResponse test(String url, Integer productId, Integer brandId, LocalDateTime appDate) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(url)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .param("appDate", appDate.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();


        String responseBody = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(responseBody, PriceResponse.class);
    }

    @Test
    public void test2() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        PriceResponse priceResponse = test("/prices/",35455, 1, localDateTime);

        assertNotNull(priceResponse);
        assertEquals( "25.45 EUR", priceResponse.price());
    }

    @Test
    public void test3() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        PriceResponse priceResponse = test("/prices/",35455, 1, localDateTime);

        assertNotNull(priceResponse);
        assertEquals("35.50 EUR", priceResponse.price());
    }

    @Test
    public void test4() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        PriceResponse priceResponse = test("/prices/",35455, 1, localDateTime);

        assertNotNull(priceResponse);
        assertEquals("30.50 EUR", priceResponse.price());
    }

    @Test
    public void test5() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        PriceResponse priceResponse = test("/prices/",35455, 1, localDateTime);

        assertNotNull(priceResponse);
        assertEquals("38.95 EUR", priceResponse.price());
    }

    @Test
    public void testNotFoundPrice() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        PriceResponse priceResponse = test("/prices/",35456, 1, localDateTime);

        assertNotNull(priceResponse);
        assertEquals(HttpStatusCode.valueOf(404), priceResponse.);
    }
    @Test
    public void testBadUrl() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        PriceResponse priceResponse = test("/prices",35455, 1, localDateTime);

        assertNotNull(priceResponse);
        assertEquals(HttpStatusCode.valueOf(404), priceResponse.);
    }
}
