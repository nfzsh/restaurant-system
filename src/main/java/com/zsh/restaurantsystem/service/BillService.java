package com.zsh.restaurantsystem.service;

import com.zsh.restaurantsystem.entity.Bill;
import com.zsh.restaurantsystem.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class BillService {
    @Autowired
    BillRepository billRepository;

    public Bill setBill(Bill bill){
        billRepository.save(bill);
        return billRepository.refresh(bill);
    }
}
