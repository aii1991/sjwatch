package com.boiledcoffee.sjwatch.model.query;


import java.io.Serializable;

/**
 *  排序字段 1=ASC,2=DESC,
 * Created by juha on 2018/1/26.
 */
public class GoodsQuery implements Serializable{
    private String name;
    private String number;
    private long type;
    private long tBId;
    private int isHot = -1;
    private int isRecommend = -1;
    private int sex = -1;
    private int sortName;
    private int sortNumber;
    private int sortTime = 2; //1=createTime ASC,2=createTime DESC,3=modifyTime ASC,4=modifyTime DESC
    private int sortSalePrice;
    private int sortWholesale;
    private int sortPurchasePrice;
    private int sortStock;
    private int sortBrand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long gettBId() {
        return tBId;
    }

    public void settBId(long tBId) {
        this.tBId = tBId;
    }

    public int getSortName() {
        return sortName;
    }

    public void setSortName(int sortName) {
        this.sortName = sortName;
    }

    public int getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(int sortNumber) {
        this.sortNumber = sortNumber;
    }

    public int getSortTime() {
        return sortTime;
    }

    public void setSortTime(int sortTime) {
        this.sortTime = sortTime;
    }

    public int getSortSalePrice() {
        return sortSalePrice;
    }

    public void setSortSalePrice(int sortSalePrice) {
        this.sortSalePrice = sortSalePrice;
    }

    public int getSortWholesale() {
        return sortWholesale;
    }

    public void setSortWholesale(int sortWholesale) {
        this.sortWholesale = sortWholesale;
    }

    public int getSortPurchasePrice() {
        return sortPurchasePrice;
    }

    public void setSortPurchasePrice(int sortPurchasePrice) {
        this.sortPurchasePrice = sortPurchasePrice;
    }

    public int getSortStock() {
        return sortStock;
    }

    public void setSortStock(int sortStock) {
        this.sortStock = sortStock;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSortBrand() {
        return sortBrand;
    }

    public void setSortBrand(int sortBrand) {
        this.sortBrand = sortBrand;
    }
}
