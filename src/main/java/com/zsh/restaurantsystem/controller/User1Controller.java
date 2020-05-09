package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Tables;
import com.zsh.restaurantsystem.repository.BillRepository;
import com.zsh.restaurantsystem.repository.ListRepository;
import com.zsh.restaurantsystem.repository.TablesRepository;
import com.zsh.restaurantsystem.service.User1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user1")
public class User1Controller {
    @Autowired
    User1Service user1Service;

    //获取全部类型桌子及数量
    @GetMapping("/getTableNumAll")
    public Map getTableNumAll() {
        return Map.of("tableNumAll", user1Service.findTableNumBy());
    }

    //获取各种类型桌子剩余数量
    @GetMapping("/getTableNum")
    public Map getTableNum() {
        return Map.of("tableNum", user1Service.findTableNumByStatue());
    }

    //获取全部桌子信息
    @GetMapping("/getAllTable")
    public Map getAllTable() {
        return Map.of("tables", user1Service.findAllTables());
    }

    //开台
    @GetMapping("/updateStatue/{tid}")
    public Map updateTableStatue(@PathVariable("tid") int tid) {
        return Map.of("tables", user1Service.updateTableStatue(tid));
    }

    //结算
    @GetMapping("/addBill/{tid}")
    public Map addBill(@PathVariable("tid") int tid) {
        return Map.of("bill",user1Service.addBill(tid));
    }
    //付款
    @GetMapping("/addBill/{bid}")
    public Map payBill(@PathVariable("bid") int bid) {
        user1Service.payBill(bid);
        return Map.of("tables",user1Service.findAllTables());
    }
}
