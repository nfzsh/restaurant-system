package com.zsh.restaurantsystem.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zsh.restaurantsystem.entity.RedisQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private final RedisTemplate<String,Object> redisTemplate;
    private ListOperations listOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    @PostConstruct
    private void init(){
        listOperations = redisTemplate.opsForList();
    }

    public Long incr(long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong("0", redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }

        return increment;
    }

    //当日最后一刻毫秒数
    public Long getCurrent2TodayEndMillisTime() {
        Calendar todayEnd = Calendar.getInstance();
        // Calendar.HOUR 12小时制
        // HOUR_OF_DAY 24小时制
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis()-new Date().getTime();
    }

    public void add(RedisQueue redisQueue,int tn) {
        listOperations.rightPush(tn+"", redisQueue);
    }
    public String deleteFirst(int tn) {
        String id = listOperations.index(tn+"", 0).toString();
        listOperations.trim(tn+"", 1, -1);
        return id;
    }

    public int findOne(final String uid,int tn){
        int index = -1;
        String s = JSON.toJSONString(listOperations.range(tn+"", 0, -1));
        List<RedisQueue> redisQueueList = JSON.parseObject(s,new TypeReference<List<RedisQueue>>(){});
        for(int i = 0; i< redisQueueList.size(); i++){

            RedisQueue a = redisQueueList.get(i);
            if(a.getUid().equals(uid))
                index = i;
        }
        return index;
    }
    public String findOneNum(final String uid,int tn){
        RedisQueue index = null;
        String s = JSON.toJSONString(listOperations.range(tn+"", 0, -1));
        List<RedisQueue> redisQueueList = JSON.parseObject(s,new TypeReference<List<RedisQueue>>(){});
        for(int i = 0; i< redisQueueList.size(); i++){

            RedisQueue a = redisQueueList.get(i);
            if(a.getUid().equals(uid))
                index = a;
        }
        return index.getNum();
    }
    public ArrayList<RedisQueue> findAll(int tn){
        return (ArrayList<RedisQueue>)listOperations.range(tn+"", 0, -1);
    }
}
