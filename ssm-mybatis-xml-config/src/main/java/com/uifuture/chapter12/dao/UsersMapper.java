package com.uifuture.chapter12.dao;


import com.uifuture.chapter12.entity.Users;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UsersMapper {
    /**
     * 根据ID删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 批量插入所有数据
     *
     * @param record
     * @return
     */
    int insertList(List<Users> record);

    /**
     * 插入所有数据
     *
     * @param record
     * @return
     */
    int insert(Users record);

    /**
     * 插入非空数据
     *
     * @param record
     * @return
     */
    int insertSelective(Users record);

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     */
    Users selectByPrimaryKey(Integer id);

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     */
    Users selectUsersPrimaryKey(Integer id);

    Users selectuserResultMap(Integer id);

    List<Users> selectUserIn(List<Integer> list);

    List<Users> findUsers(String username, Integer age, Date createTime);

    List<Users> findUserResultMap(String username, Integer age);

    Map<String, Object> selectUsers(Integer id);

    /**
     * 通过id修改非空数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKeySelectiveTrim(Users record);

    /**
     * 通过id修改所有的数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Users record);
}