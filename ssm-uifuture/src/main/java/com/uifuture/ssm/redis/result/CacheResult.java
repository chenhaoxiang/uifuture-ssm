package com.uifuture.ssm.redis.result;

import com.alibaba.fastjson.JSON;
import lombok.Setter;

import java.util.List;
import java.util.StringJoiner;

public class CacheResult {

    @Setter
    private boolean exist = false;

    @Setter
    private String data;


    public boolean isKeyExist() {
        return exist;
    }


    /**
     * 获取值
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> clazz) {
        if (data == null) {
            return null;
        }
        if (String.class.equals(clazz)) {
            return (T) data;
        }
        return JSON.parseObject(data, clazz);
    }

    public String getString() {
        return data;
    }


    public <T> List<T> getList(Class<T> clazz) {
        if (data == null) {
            return null;
        }
        return JSON.parseArray(data, clazz);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CacheResult.class.getSimpleName() + "[", "]")
                .add("exist=" + exist)
                .add("data='" + data + "'")
                .toString();
    }
}
