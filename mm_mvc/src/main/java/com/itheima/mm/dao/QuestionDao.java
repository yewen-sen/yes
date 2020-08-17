package com.itheima.mm.dao;

import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.Question;

import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-08-02  15:45
 */
public interface QuestionDao {
    Long findCountByCourseId(String id);

    Long findTotalBasicCount(QueryPageBean queryPageBean);

    List<Question> findBasicPageList(QueryPageBean queryPageBean);

    void add(Question question);

    void addQuestionTag(Map paramMap);
}
