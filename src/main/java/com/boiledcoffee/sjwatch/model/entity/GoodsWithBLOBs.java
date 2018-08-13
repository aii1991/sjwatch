package com.boiledcoffee.sjwatch.model.entity;

public class GoodsWithBLOBs extends Goods {
    private String descr;

    private String sources;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources == null ? null : sources.trim();
    }
}