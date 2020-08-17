package com.itheima.service;

import com.itheima.pojo.Items;

import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
public interface ItemsService {

    Items findById(int id);

    /**
     * 查询所有
     * @return
     */
    List<Items> findAll();
}
