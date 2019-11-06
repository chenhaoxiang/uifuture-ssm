package com.uifuture.chapter12.dao;


import com.uifuture.chapter12.entity.Blog;
import com.uifuture.chapter12.entity.BlogExt;
import com.uifuture.chapter12.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    /**
     * 通过bind元素查询博客
     *
     * @param blog
     * @return
     */
    List<Blog> selectBlogsLike(Blog blog);

    /**
     * 查询所有，演示row原始语言
     *
     * @return
     */
    List<Blog> selectAll();

    /**
     * @param title
     * @return
     */
    List<Blog> selectBlogsTitleLike(@Param("title") String title);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    /**
     * 通过博客id查询博客
     *
     * @param id
     * @return
     */
    BlogExt selectBlogExtById(Integer id);

    /**
     * 通过博客id查询文章
     *
     * @param id
     * @return
     */
    Post selectPostsForBlog(Integer id);

    /**
     * 通过博客id查询文章
     *
     * @param id
     * @return
     */
    BlogExt selectBlogExt(Integer id);

    /**
     * 只有当id为2时，才进行查询博客
     *
     * @param id
     * @return
     */
    Blog selectBlogDiscriminator(Integer id);
}