package com.zsh.restaurantsystem.service;

import com.zsh.restaurantsystem.entity.Bill;
import com.zsh.restaurantsystem.entity.Tables;
import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.repository.BillRepository;
import com.zsh.restaurantsystem.repository.ListRepository;
import com.zsh.restaurantsystem.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class User1Service {
    @Autowired
    TablesRepository tablesRepository;
    @Autowired
    ListRepository listRepository;
    @Autowired
    BillRepository billRepository;

    public List findTableNumBy() {
        return tablesRepository.findTableNumBy();
    }

    public List findTableNumByStatue() {
        return tablesRepository.findTableNumByStatue();
    }

    public List findAllTables() {
        return tablesRepository.findAll();
    }

    public List updateTableStatue(int tid) {
        Tables t = tablesRepository.findById(tid).get();
        t.setStatue(1);
        tablesRepository.save(t);
        return tablesRepository.findAll();
    }

    public Bill addBill(int tid) {
        Tables t = tablesRepository.findById(tid).get();
        t.setStatue(0);
        tablesRepository.save(t);
        List<com.zsh.restaurantsystem.entity.List> l = listRepository.getList(tid);
        float p = 0;
        for (int i = 0; i < l.size(); i++) {
            p += l.get(i).getPrice();
        }
        Bill b = new Bill();
        b.setPrice(p);
        b.setUser(l.get(0).getUser());
        billRepository.save(b);
        listRepository.setBillId(tid, b.getId());
        return b;
    }
    public void payBill(int bid) {
        LocalDate d = LocalDate.now();
        billRepository.payBill(bid, 1,d);
    }
    public List<com.zsh.restaurantsystem.entity.List> getListsByTable(int tid) {
        return listRepository.getList(tid);
    }

}
