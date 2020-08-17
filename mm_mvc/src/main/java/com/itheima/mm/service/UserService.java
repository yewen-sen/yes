package com.itheima.mm.service;

import com.itheima.mm.dao.UserDao;
import com.itheima.mm.pojo.User;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-08-02  10:06
 */
public class UserService {
    /**
     * 处理登录
     * @param user
     * @return
     */
    public User login(User user) throws Exception {
        //1. 校验用户名,根据用户名到数据库查找用户
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User loginUser = userDao.findByUsername(user.getUsername());

        SqlSessionFactoryUtils.commitAndClose(sqlSession);

        //2. 判断loginUser是否为null
        if (loginUser != null) {
            //用户名正确
            //判断密码是否正确
            if (user.getPassword().equals(loginUser.getPassword())) {
                //密码正确
                //登录成功
                return loginUser;
            }else {
                //密码错误
                throw new RuntimeException("密码错误");
            }
        }else {
            //用户名错误
            throw new RuntimeException("用户名错误");
        }
    }
}
