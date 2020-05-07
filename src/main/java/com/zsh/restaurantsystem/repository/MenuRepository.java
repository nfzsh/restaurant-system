package com.zsh.restaurantsystem.repository;


import com.zsh.restaurantsystem.entity.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CustomizedRepoistory<Menu, Integer> {
    @Query("SELECT m FROM Menu m WHERE m.type=:type")
    List<Menu> findMenusByType(@Param("type") int type);
}
