package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.RedisQueue;
import com.zsh.restaurantsystem.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class QueueController {
    @Autowired
    private RedisRepository redisRepository;

    @RequestMapping("/")
    public String index() {
        return "";
    }

    @RequestMapping(value = "/selectIndex", method = RequestMethod.POST)
    public ResponseEntity<String> selectIndex(
            @RequestParam String uid){
        return new ResponseEntity<>(String.valueOf(redisRepository.findOne(uid)), HttpStatus.OK);
    }

    @RequestMapping("/values")
    public @ResponseBody ArrayList<RedisQueue> findAll() {
        return redisRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(
            @RequestParam String uid) {
        String a = redisRepository.incr(redisRepository.getCurrent2TodayEndMillisTime()).toString();
        RedisQueue redisQueue = new RedisQueue(uid, a);
        redisRepository.add(redisQueue);
        return new ResponseEntity<>(a,HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete() {
        redisRepository.deleteFirst();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
