package com.zsh.restaurantsystem.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RedisQueue implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uid;
    private String num;

    public RedisQueue(String uid, String num) {
        this.uid = uid;
        this.num = num;

    }
    public RedisQueue() {}
    @Override
    public String toString(){
        return "Queue{" + "id=" +uid + '\''  + ", num =" + num + "}";
    }
}

