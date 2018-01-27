package com.boiledcoffee.sjwatch.model.entity;

import java.util.Date;

public class Goods {
    private Long id;

    private Long tBId;

    private String name;

    private String number;

    private Long type;

    private Double salePrice;

    private Double wholesale;

    private Double purchasePrice;

    private Integer stock;

    private String sources;

    private Date createTime;

    private Date modifyTime;

    private String descr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long gettBId() {
        return tBId;
    }

    public void settBId(Long tBId) {
        this.tBId = tBId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getWholesale() {
        return wholesale;
    }

    public void setWholesale(Double wholesale) {
        this.wholesale = wholesale;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources == null ? null : sources.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }
}