package com.boiledcoffee.sjwatch.dao;

public interface BaseMapper<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    long insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}