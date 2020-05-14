package com.zsh.restaurantsystem.service;

import com.zsh.restaurantsystem.entity.Bill;
import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillService {
    @Autowired
    BillRepository billRepository;

    public void setBill(Bill bill) {
        billRepository.save(bill);
    }
    public Bill updateBill(Bill bill) {
        Bill b = billRepository.findById(bill.getId()).get();
        b.setNote(bill.getNote());
        billRepository.save(b);
        return billRepository.refresh(bill);
    }

    public void deleteBill(Bill bill) {
        billRepository.deleteById(bill.getId());
    }

    public Optional<Bill> getBill(Bill bill) {
        return billRepository.findById(bill.getId());
    }

    public List<Bill> getBillByUid(User user) {
        return billRepository.getBill(user.getId());
    }

    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }
}
