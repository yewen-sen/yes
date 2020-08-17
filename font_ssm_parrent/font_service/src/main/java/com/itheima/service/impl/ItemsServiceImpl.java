package com.itheima.service.impl;

import com.itheima.dao.ItemsDao;
import com.itheima.pojo.Items;
import com.itheima.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;
    @Override
    public Items findById(int id) {
        return itemsDao.findById(id);
    }

    @Override
    public List<Items> findAll() {
        return itemsDao.findAll();
    }
}
