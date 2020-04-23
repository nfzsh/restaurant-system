package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Bill;
import com.zsh.restaurantsystem.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
