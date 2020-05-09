package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Bill;
import com.zsh.restaurantsystem.entity.List;
import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.service.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/{uid}/bill")
public class ListController {
    @Autowired
    ListService listService;

    @PostMapping("/add")
    public void setList(@RequestBody List list, @PathVariable int uid) {
        User user = new User();
        user.setId(uid);
        list.setUser(user);
        listService.setList(list);
    }

    @PostMapping("/delete")
    public void deleteList(@RequestBody List list) {
        listService.deleteList(list);
    }

    //根据bill_id查详情
    @PostMapping("/select_bill")
    public void selectListByBill(@RequestBody Bill bill, @PathVariable int uid) {
        listService.getListByBid(bill.getId());
    }

    //当前点菜情况
    @PostMapping("/select_now")
    public void selectListNow(@PathVariable int uid) {
        listService.getListNow(uid);
    }

    //更新菜单bid
    @PostMapping("/update_bid")
    public void setBillId(@RequestBody Bill bill, @PathVariable int uid) {
        listService.setBillId(bill, uid);
    }

    //更新菜品数量
    @PostMapping("/update_num")
    public void updateNum(@RequestBody List list, @PathVariable int uid) {
        listService.setListNum(list, uid);
    }
}
