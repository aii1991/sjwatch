package com.boiledcoffee.sjwatch.model.communication;

import java.util.List;

/**
 *
 * Created by ${juha} on 2018/2/7.
 */
public class PageRspData<T> {
    private long total;
    private List<T> list;

    public PageRspData(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getList() {
        return list;
    }
}
