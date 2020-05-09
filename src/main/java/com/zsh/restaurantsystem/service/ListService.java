package com.zsh.restaurantsystem.service;

import com.zsh.restaurantsystem.entity.Bill;
import com.zsh.restaurantsystem.entity.List;
import com.zsh.restaurantsystem.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ListService {
    @Autowired
    ListRepository listRepository;

    public List setList(List list) {
        list.setPrice(listRepository.getPrice(list.getMenu().getId()));
        listRepository.save(list);
        return listRepository.refresh(list);
    }

    public void deleteList(List list) {
        listRepository.deleteById(list.getId());
    }

    //根据bill查详情
    public java.util.List<List> getListByBid(int bid) {
        return listRepository.getListByBid(bid);
    }

    //当前点菜情况
    public java.util.List<List> getListNow(int uid) {
        return listRepository.getList(uid);
    }

    //更新订单bid
    public void setBillId(Bill bill, int uid) {
        listRepository.setBillId(uid, bill.getId());
    }

    //更新菜品数量
    public void setListNum(List list, int uid) {
        listRepository.setBillNum(list.getNum(), uid);
    }

    //查询菜品状态
    public java.util.List<List> getNowListByOrder() {
        return listRepository.getNowListOrderByStatue();
    }
    //更新菜品状态
    public void setListStatue(int statue, int lid) {
        listRepository.setBillStatue(statue, lid);
    }
}
