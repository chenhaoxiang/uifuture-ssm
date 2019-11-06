package com.uifuture.chapter12.dao;

import com.uifuture.chapter12.entity.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class BlogMapperTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogMapperTest.class);

    @Test
    public void selectBlogsLike() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setTitle("a");
            System.out.println("===================" + mapper.selectBlogsLike(blog));
        } finally {
            session.close();
        }
    }


    @Test
    public void selectBlogsTitleLike() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            System.out.println("===================" + mapper.selectBlogsTitleLike("a"));
        } finally {
            session.close();
        }
    }

    @Test
    public void selectAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            PostMapper mapper = session.getMapper(PostMapper.class);
            System.out.println("===================" + mapper.selectAll());
        } finally {
            session.close();
        }
    }

    /**
     * 插入数据，记得commit
     *
     * @throws IOException
     */
    @Test
    public void insert() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setTitle("2632");
            blog.setAuthorId(2);
            System.out.println("===================" + mapper.insert(blog));
            session.commit();
            System.out.println(blog);
        } finally {
            session.close();
        }
    }

    /**
     * 修改数据，也记得commit
     *
     * @throws IOException
     */
    @Test
    public void insertSelective() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setTitle("15266");
            blog.setAuthorId(2);
            System.out.println("===================" + mapper.insertSelective(blog));
            session.commit();
            System.out.println(blog);
        } finally {
            session.close();
        }
    }
}
