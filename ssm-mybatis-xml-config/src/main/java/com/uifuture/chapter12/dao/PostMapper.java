package com.uifuture.chapter12.dao;


import com.uifuture.chapter12.entity.Post;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {
    @Select("SELECT * FROM post")
    List<Post> selectAll();
}