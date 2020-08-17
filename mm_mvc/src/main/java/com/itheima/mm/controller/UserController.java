package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constant.Constants;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-08-02  10:03
 */
@Controller
public class UserController {
    private UserService userService = new UserService();
    @RequestMapping("/user/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数，封装到user对象中
            User user = JsonUtils.parseJSON2Object(request, User.class);
            //2. 调用业务层的方法，进行登录校验
            User loginUser = userService.login(user);
            //3. 将loginUser存储到session中
            request.getSession().setAttribute(Constants.LOGIN_USER,loginUser);
            //将result转换陈json字符串输出到浏览器
            JsonUtils.printResult(response,new Result(true,"登录成功",loginUser));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,e.getMessage(),null));
        }
    }
    @RequestMapping("/user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1. 销毁session
        request.getSession().invalidate();
        //2. 向客户端响应数据
        JsonUtils.printResult(response,new Result(true,"退出成功",null));
    }
}
