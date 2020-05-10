package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.RedisQueue;
import com.zsh.restaurantsystem.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user1")
public class QueueController {
    @Autowired
    private RedisRepository redisRepository;

    @GetMapping("/selectIndex/{tn}/{uid}")
    public Map selectIndex(
            @PathVariable String uid, @PathVariable int tn) {
        return Map.of(tn + "", redisRepository.findOne(uid, tn));
    }

    @GetMapping("/values/{tn}")
    public @ResponseBody
    Map findAll(@PathVariable int tn) {
        return Map.of("queue", redisRepository.findAll(tn));
    }

    @GetMapping("/add/{tn}/{uid}")
    public Map add(
            @PathVariable String uid, @PathVariable int tn) {
        String a = redisRepository.incr(redisRepository.getCurrent2TodayEndMillisTime()).toString();
        a = tn + "0" + a;
        RedisQueue redisQueue = new RedisQueue(uid, a);
        redisRepository.add(redisQueue, tn);
        return Map.of("num", a);
    }

    @GetMapping("/delete/{tn}")
    public void delete(@PathVariable int tn) {
        redisRepository.deleteFirst(tn);
    }
}
