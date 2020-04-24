package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends CustomizedRepoistory<Bill, Integer> {
    @Query("SELECT b FROM Bill b WHERE b.user.id =:id")
    List<Bill> getBill(int id);
}
