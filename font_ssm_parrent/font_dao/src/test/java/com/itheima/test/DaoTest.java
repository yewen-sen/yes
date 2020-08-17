package com.itheima.test;

import com.itheima.dao.ItemsDao;
import com.itheima.pojo.Items;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: No Description
 * User: Eric
 */
public class DaoTest {

    @Test
    public void tt(){
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        ItemsDao itemsDao = app.getBean(ItemsDao.class);
        Items items = itemsDao.findById(17);
        System.out.println(items);

    }
}
