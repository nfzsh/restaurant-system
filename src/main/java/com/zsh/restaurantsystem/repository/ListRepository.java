package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends CustomizedRepoistory<List, Integer> {
    //根据bill查详情
    @Query("SELECT l FROM List l WHERE l.user.id=:uid AND l.bill.id=:bid")
    java.util.List<List> getList(@Param("uid") int uid, @Param("bid") int bid);

    //当前点菜情况
    @Query("SELECT l FROM List l WHERE l.user.id=:uid AND l.bill.id=0")
    java.util.List<List> getList(@Param("uid") int uid);

    //更新订单bid
    @Modifying
    @Query("UPDATE List l SET l.bill.id=:bid WHERE l.user.id=:uid AND l.bill.id=0")
    void setBillId(@Param("uid") int uid,@Param("bid") int bid);
    //更新菜品数量
    @Modifying
    @Query("UPDATE List l SET l.num=:num WHERE l.user.id=:uid AND l.bill.id=0")
    void setBillNum(@Param("num") int num,@Param("uid") int uid);
    //获取菜价
    @Query("SELECT m.price FROM Menu m WHERE m.id=:id")
    Float getPrice(@Param("id") int id);
}
