package com.ssm.dao;

import org.springframework.stereotype.Repository;

/**
 * 所有数据库持久化操作超类
 *
 * @param <T>
 */
@Repository
public interface BaseMapping<T> {
    /**
     * 根据ID查询数据
     * @param id 实体类的ID
     * @return
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 执行完全插入操作 也就是不能有null值
     * @param entity
     * @return
     */
    Integer insert(T entity);
    /**
     * 执行部分插入操作 也就是null值不插入
     * @param entity
     * @return
     */
    Integer insertSelective(T entity);
    /**
     * 执行更新操作 全部更新
     * @param entity
     * @return
     */
    Integer updateByPrimaryKey(T entity);
    /**
     * 执行部分更新操作 为null的不会被插入
     * @param entity
     * @return
     */
    Integer updateByPrimaryKeySelective(T entity);
    /**
     * 真正意义上的删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);
}
