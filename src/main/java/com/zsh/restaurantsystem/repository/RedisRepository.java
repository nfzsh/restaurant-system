package com.zsh.restaurantsystem.repository;

import java.util.ArrayList;
import com.zsh.restaurantsystem.entity.RedisQueue;

public interface RedisRepository {
    /**
     * 查询完整队列
     */
    ArrayList<RedisQueue> findAll();

    /**
     * 排队
     */
    void add(RedisQueue redisQueue);

    /**
     * 出队
     */
    String deleteFirst();

    /**
     * 查找排序
     */
    int findOne(String id);
    Long incr(long liveTime);
    Long getCurrent2TodayEndMillisTime();
}
