package com.zsh.restaurantsystem.repository;

import java.util.ArrayList;
import com.zsh.restaurantsystem.entity.RedisQueue;

public interface RedisRepository {
    /**
     * 查询完整队列
     */
    ArrayList<RedisQueue> findAll(int tn);

    /**
     * 排队
     */
    void add(RedisQueue redisQueue,int tn);

    /**
     * 出队
     */
    String deleteFirst(int tn);

    /**
     * 查找排序
     */
    String findOne(String id,int tn);
    Long incr(long liveTime);
    Long getCurrent2TodayEndMillisTime();
}
