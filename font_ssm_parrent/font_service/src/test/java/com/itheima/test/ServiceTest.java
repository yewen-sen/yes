package com.itheima.test;

import com.itheima.dao.ItemsDao;
import com.itheima.pojo.Items;
import com.itheima.service.ItemsService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: No Description
 * User: Eric
 */
public class ServiceTest {

    @Test
    public void tt(){
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-service.xml");
        ItemsService itemsService = app.getBean(ItemsService.class);
        Items items = itemsService.findById(17);
        System.out.println(items);
    }
}
