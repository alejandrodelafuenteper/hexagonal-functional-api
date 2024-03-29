package com.example.myapiwithh2.pricecalculate.domain;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Price {

    private Integer id;
    private Integer brandId;
    private Date startDate;
    private Date endDate;
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
    private Date lastUpdate;
    private String lastUpdateBy;


    public Price() {

    }

    public Price(Integer id, Integer brandId, Date startDate, Date endDate, Integer priceList, Integer productId,
                 Integer priority, BigDecimal price, String currency, Date lastUpdate, String lastUpdateBy) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price1 = (Price) o;
        return Objects.equals(id, price1.id) && Objects.equals(brandId, price1.brandId) && Objects.equals(startDate, price1.startDate) && Objects.equals(endDate, price1.endDate) && Objects.equals(priceList, price1.priceList) && Objects.equals(productId, price1.productId) && Objects.equals(priority, price1.priority) && Objects.equals(price, price1.price) && Objects.equals(currency, price1.currency) && Objects.equals(lastUpdate, price1.lastUpdate) && Objects.equals(lastUpdateBy, price1.lastUpdateBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, startDate, endDate, priceList, productId, priority, price, currency, lastUpdate, lastUpdateBy);
    }
}