package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Bill;
import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    BillService billService;

    @PostMapping("/add")
    public void setBill(@RequestBody Bill bill){
        billService.setBill(bill);
    }
    @PostMapping("/update")
    public void updateBill(@RequestBody Bill bill){
        billService.setBill(bill);
    }
    //user.id
    @PostMapping("/select_uid")
    public Map selectBill(@RequestBody User user){
        return Map.of("bill",billService.getBillByUid(user));
    }
    //bill.id
    @PostMapping("/select_id")
    public Map selectBill(@RequestBody Bill bill){
        return Map.of("bill",billService.getBill(bill));
    }
//    @GetMapping("/select_all")
//    public Map selectAll(){
//        return Map.of("bills",billService.getAllBill());
//    }
}
