package com.itheima.mm.service;

import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.TagDao;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-08-02  11:09
 */
public class CourseService {
    public void add(Course course) throws Exception {
        //调用Dao层的方法添加学科信息
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        courseDao.add(course);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    public PageResult findByPage(QueryPageBean queryPageBean) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        //1. 调用Dao层的方法，查询学科的总条数
        Long total = courseDao.findTotalCount(queryPageBean);

        //2. 调用Dao层的方法，查询当前页的学科列表
        List<Course> courseList = courseDao.findPageList(queryPageBean);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return new PageResult(total,courseList);
    }

    public void update(Course course) throws Exception {
        //调用Dao层的方法修改学科信息
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);

        courseDao.update(course);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    public void deleteById(String id) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        //1. 判断当前要删除的学科是否有关联的二级目录
        CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
        //根据courseId到t_catalog表中查询数据条数
        Long catalogCount = catalogDao.findCountByCourseId(id);
        if (catalogCount > 0) {
            //有关联的二级目录，不能删除
            throw new RuntimeException("有关联的二级目录，不能删除");
        }

        //2. 判断当前要删除的学科是否有关联的标签
        TagDao tagDao = sqlSession.getMapper(TagDao.class);
        Long tagCount = tagDao.findCountByCourseId(id);
        if (tagCount > 0) {
            //有关联的标签，不能删除
            throw new RuntimeException("有关联的标签，不能删除");
        }

        //3. 判断当前要删除的学科是否有关联的题目
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
        Long questionCount = questionDao.findCountByCourseId(id);
        if (questionCount > 0) {
            //有关联的题目，不能删除
            throw new RuntimeException("有关联的题目，不能删除");
        }

        //4. 如果上述三个关联都没有，则调用dao层的方法进行根据id删除
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        courseDao.deleteById(id);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    public List<Course> findAll(String status) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        List<Course> courseList = courseDao.findAll(status);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return courseList;
    }
}
