package com.uifuture.ssm.redis;

import com.alibaba.fastjson.JSON;
import com.uifuture.ssm.redis.result.CacheResult;
import org.apache.ibatis.cache.CacheException;
import org.springframework.util.StringUtils;

public abstract class AbstractClient implements Client {

    /**
     * TODO 存在真正的值就是这样的可能
     */
    private final static String nullValue = "!@#$*!#@&%$";

    /**
     * 设置值
     * @param key
     * @param obj
     * @param expire 单位秒
     */
    @Override
    public void set(String key, Object obj, int expire) {
        if (StringUtils.isEmpty(key)) {
            throw new CacheException("key cant't be empty");
        }
        String value;
        if (obj == null) {
            value = nullValue;
        } else {
            if (obj instanceof String) {
                value = (String) obj;
            } else {
                value = JSON.toJSONString(obj);
            }
        }
        doSet(key, value, expire);
    }


    @Override
    public CacheResult get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        String value = doGet(key);
        CacheResult result = new CacheResult();
        if (value == null) {
            result.setExist(false);
            result.setData(null);
            return result;
        }
        result.setExist(true);
        if (nullValue.equals(value)) {
            result.setData(null);
            return result;
        }
        result.setData(value);
        return result;
    }


    public String getRealKey(String prefix, String key) {
        return prefix + ":" + key;
    }


    /**
     * 设置值
     *
     * @param key
     * @param value
     * @param expire
     */
    protected abstract void doSet(String key, String value, int expire);

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    protected abstract String doGet(String key);


}
