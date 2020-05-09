package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends CustomizedRepoistory<List, Integer> {
    //根据bill查详情
    @Query("SELECT l FROM List l WHERE l.bill.id=:bid")
    java.util.List<List> getListByBid(@Param("bid") int bid);

    //当前点菜情况
    @Query("SELECT l FROM List l WHERE l.tables.id=:tid AND l.bill IS NULL")
    java.util.List<List> getList(@Param("tid") int tid);

    //更新订单bid
    @Modifying
    @Query("UPDATE List l SET l.bill.id=:bid WHERE l.tables.id=:tid AND l.bill IS NULL")
    void setBillId(@Param("tid") int tid,@Param("bid") int bid);
    //更新菜品数量
    @Modifying
    @Query("UPDATE List l SET l.num=:num WHERE l.user.id=:uid AND l.bill.id=0")
    void setBillNum(@Param("num") int num,@Param("uid") int uid);
    //获取菜价
    @Query("SELECT m.price FROM Menu m WHERE m.id=:id")
    Float getPrice(@Param("id") int id);
    //当前全部点菜情况
    @Query("SELECT l FROM List l WHERE (l.statue=1 OR l.statue=0) AND l.bill IS NULL ORDER BY l.addTime ASC ")
    java.util.List<List> getNowListOrderByStatue();
    //更新菜品状态
    @Modifying
    @Query("UPDATE List l SET l.statue=:statue WHERE l.id=:lid")
    void setBillStatue(@Param("statue") int statue,@Param("lid") int lid);
}
