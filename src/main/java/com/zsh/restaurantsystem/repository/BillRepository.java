package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.Bill;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends CustomizedRepoistory<Bill, Integer> {
    @Query("SELECT b FROM Bill b WHERE b.user.id=:id")
    List<Bill> getBill(@Param("id")int id);

    @Modifying
    @Query("UPDATE Bill b SET b.statue=:statue,b.payTime=:date WHERE b.id=:bid")
    void payBill(@Param("bid")int bid, @Param("statue")int statue, @Param("date") LocalDateTime date);
}
