package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constant.Constants;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-08-04  10:10
 */
@Controller
public class QuestionController {
    private QuestionService questionService = new QuestionService();
    @RequestMapping("/question/findBasicByPage")
    public void findBasicByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数，封装到QueryPageBean对象中
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);
            //2. 调用业务层的方法，进行分页查询
            PageResult pageResult = questionService.findBasicByPage(queryPageBean);

            JsonUtils.printResult(response,new Result(true,"获取基础题库成功",pageResult));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"获取基础题库失败"));
        }
    }

    @RequestMapping("/question/add")
    public void addQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数，并且封装到Question对象中
            Question question = JsonUtils.parseJSON2Object(request, Question.class);
            //手动设置缺失的数据: status,reviewStatus,createDate,userId
            question.setStatus(Constants.QUESTION_PRE_PUBLISH);
            question.setReviewStatus(Constants.QUESTION_PRE_REVIEW);
            question.setCreateDate(DateUtils.parseDate2String(new Date()));
            User user = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
            question.setUserId(user.getId());

            //2. 调用业务层的方法，添加题目信息
            questionService.add(question);
            JsonUtils.printResult(response,new Result(true,"添加试题成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"添加试题失败"));
        }
    }
}
