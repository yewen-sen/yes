package com.itheima.mm.dao;

import com.itheima.mm.pojo.Catalog;

import java.util.List;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-08-02  15:42
 */
public interface CatalogDao {
    Long findCountByCourseId(String courseId);

    List<Catalog> findCatalogListByCourseId(Integer courseId);
}
