## Overview

The project aims to provide a service in Spring Boot that exposes a RESTful endpoint for querying prices. The endpoint accepts the following input parameters:

- `productId`: Product identifier (Integer)
- `brandId`: Brand identifier (Integer)
- `appDate`: Application date (LocalDateTime)

The output data includes:

- `productId`: Product identifier (Integer)
- `brandId`: Brand identifier (Integer)
- `priceList`: Price list identifier (Integer)
- `startDate`: Start date (Date)
- `endDate`: End date (Date)
- `price`: Price (String) + Currency (String)

Additionally, the project emphasizes the use of functional programming and adheres to the SOLID principles and clean code practices.

## Technologies Used

- Spring Boot
- H2 Database
- Java
- Functional Programming

## Installation

1. Clone the repository:
git clone https://github.com/alejandrodelafuenteper/hexagonal-functional-api

2. Navigate to the project directory:
cd hexagonal-functional-api

3. Build the project:
./mvnw clean package

4. Run the application:
mvn spring-boot:run

## Usage

Once the application is running, you can access the endpoint using tools like Postman or cURL.

### Endpoint

GET /prices/

### Request Parameters

- 'productId': Product identifier (Integer)
- 'brandId': Brand identifier (Integer)
- 'appDate': Application date (LocalDateTime)

### Response

{  
	"productId": "product_id",  
	"brandId": "brand_id",  
	"priceList": "price_list_id",  
	"startDate": "start_date",  
	"endDate": "end_date",  
	"price": "price + currency"  
}

### Sample Request:

http://localhost:8080/prices/?productId=35455&brandId=1&appDate=2020-06-15T10:00:00

### Successful Response:

{  
    "productId": 35455,  
    "brandId": 1,  
    "priceList": 3,  
    "startDate": "2020-06-14T22:00:00.000+00:00",  
    "endDate": "2020-06-15T09:00:00.000+00:00",  
    "price": "30.50 EUR"  
}

## Integration Tests

The project includes integration tests to verify different test cases. These tests can be found in the `src/test` directory.

To run the integration tests:

./mvnw test

## Contributing

Contributions are welcome. Please feel free to open a pull request for any improvements.
