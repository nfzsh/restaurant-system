package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.Bill;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CustomizedRepoistory<Bill, Integer> {

}
