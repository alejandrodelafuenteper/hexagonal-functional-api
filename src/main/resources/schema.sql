CREATE TABLE prices
(
    id              INTEGER GENERATED BY DEFAULT AS IDENTITY,
    brand_id        INTEGER,
    start_date      TIMESTAMP,
    end_date        TIMESTAMP,
    price_list      INTEGER,
    product_id      INTEGER,
    priority        INTEGER,
    price           NUMERIC(20, 2),
    currency        VARCHAR(255),
    last_update     TIMESTAMP,
    last_update_by  VARCHAR(255),
    PRIMARY KEY (id)
);