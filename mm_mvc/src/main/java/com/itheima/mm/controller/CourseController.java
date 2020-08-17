package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constant.Constants;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.CourseService;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-08-02  11:04
 */
@Controller
public class CourseController {
    private CourseService courseService = new CourseService();
    @RequestMapping("/course/add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数封装到Course对象中
            Course course = JsonUtils.parseJSON2Object(request, Course.class);
            //2. 补全course缺少的数据
            //2.1 设置createDate
            course.setCreateDate(DateUtils.parseDate2String(new Date()));
            //2.2 设置userId
            User user = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
            course.setUserId(user.getId());
            //2.3 设置orderNo
            course.setOrderNo(2);
            //3. 调用业务层的方法保存course的信息
            courseService.add(course);

            JsonUtils.printResult(response,new Result(true,"添加学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"添加学科失败"));
        }
    }
    @RequestMapping("/course/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数封装到QueryPageBean对象
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);
            Map queryParams = queryPageBean.getQueryParams();
            queryParams.put("status",queryParams.get("status")+"");

            //2. 调用业务层的方法进行分页查询
            PageResult pageResult = courseService.findByPage(queryPageBean);
            //3. 将响应结果进行封装，并且转换成json输出到客户端
            JsonUtils.printResult(response,new Result(true,"查询学科列表成功",pageResult));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"查询学科列表失败"));
        }
    }

    @RequestMapping("/course/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数封装到Course中
            Course course = JsonUtils.parseJSON2Object(request, Course.class);
            //2. 调用业务层的方法修改学科信息
            courseService.update(course);

            //修改成功
            JsonUtils.printResult(response,new Result(true,"修改学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            //修改失败
            JsonUtils.printResult(response,new Result(false,"修改学科失败"));
        }
    }
    @RequestMapping("/course/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数id
            String id = request.getParameter("id");
            //2. 调用业务层的方法，根据id删除学科
            courseService.deleteById(id);
            //删除成功
            JsonUtils.printResult(response,new Result(true,"删除学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/course/findAll")
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数status
            String status = request.getParameter("status");
            //调用业务层的方法，查询所有学科信息
            List<Course> courseList = courseService.findAll(status);
            //封装响应结果
            JsonUtils.printResult(response,new Result(true,"获取所有学科成功",courseList));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"获取所有学科失败"));
        }

    }
}
