package com.uifuture.ssm.redis;

import com.uifuture.ssm.redis.result.CacheResult;

public interface Client {

    /**
     * 返回实际的缓存过期时间
     *
     * @param key
     * @param obj
     * @param expire
     * @return
     */
    public void set(String key, Object obj, int expire);

    public CacheResult get(String key);

    public void delete(String key);

}
