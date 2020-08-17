package com.itheima.mm.dao;

import com.itheima.mm.pojo.Tag;

import java.util.List;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-08-02  15:44
 */
public interface TagDao {
    Long findCountByCourseId(String id);

    List<Tag> findTagListByCourseId(Integer courseId);
}
