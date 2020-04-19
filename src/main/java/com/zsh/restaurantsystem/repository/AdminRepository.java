package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.Admin;
import com.zsh.restaurantsystem.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends CustomizedRepoistory<Admin, Integer> {
    @Query("SELECT a FROM Admin a WHERE a.name=:name")
    Admin findAdmin(@Param("name") String name);

    @Query("SELECT a.id FROM Admin a WHERE a.name=:name")
    int findId(@Param("name") String name);

    @Modifying
    @Query("UPDATE Admin a SET a.name=:name,a.password=:password," +
            "a.authority=:authority WHERE a.id=:id")
    void UpdateAdmin(@Param("name") String name, @Param("password") String password,
                     @Param("authority") int authority, @Param("id") int id);

    @Modifying
    @Query("UPDATE Admin a SET a.name=:name,a.authority=:authority WHERE a.id=:id")
    void UpdateAdmin(@Param("name") String name,
                     @Param("authority") int authority, @Param("id") int id);
}
