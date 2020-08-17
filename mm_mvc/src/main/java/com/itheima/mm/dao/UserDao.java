package com.itheima.mm.dao;

import com.itheima.mm.pojo.User;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-08-02  10:12
 */
public interface UserDao {
    User findByUsername(String username);
}
