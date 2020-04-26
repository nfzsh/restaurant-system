package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends CustomizedRepoistory<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%',:name,'%')")
    List<User> getUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.openid=:openid")
    User getUserByOpenid(@Param("openid") String openid);

    @Query("SELECT u FROM User u WHERE u.phone_num LIKE CONCAT('%',:phone_num,'%')")
    List<User> getUser(@Param("phone_num") String phone_num);

    @Modifying
    @Query("DELETE FROM User WHERE openid=:openid")
    void deleteUserByOpenid(@Param("openid") int openid);

    //根据openid更新
    @Modifying
    @Query("UPDATE User u SET u.phone_num=:phone_num,u.name=:name,u.birth=:birth WHERE u.openid=:openid")
    void updateUser(@Param("openid") int openid, @Param("name") String name,
                    @Param("birth") LocalDate birth, @Param("phone_num") String phone_num);


}
