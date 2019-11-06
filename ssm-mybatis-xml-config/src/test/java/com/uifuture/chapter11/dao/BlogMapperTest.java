package com.uifuture.chapter11.dao;

import com.uifuture.chapter12.dao.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class BlogMapperTest {
    @Test
    public void selectBlogExtById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            System.out.println("===================" + mapper.selectBlogExt(1));
        } finally {
            session.close();
        }
    }

    @Test
    public void selectPostsForBlog() {
    }

    @Test
    public void selectBlogExtDiscriminator() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            System.out.println("=========1==========" + mapper.selectBlogDiscriminator(1));
            System.out.println("=========2==========" + mapper.selectBlogDiscriminator(2));
        } finally {
            session.close();
        }
    }
}
