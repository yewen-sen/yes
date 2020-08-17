package com.itheima.mm.dao;

import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.Course;

import java.util.List;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-08-02  11:11
 */
public interface CourseDao {
    void add(Course course);

    Long findTotalCount(QueryPageBean queryPageBean);

    List<Course> findPageList(QueryPageBean queryPageBean);

    void update(Course course);

    void deleteById(String id);

    List<Course> findAll(String status);

}
