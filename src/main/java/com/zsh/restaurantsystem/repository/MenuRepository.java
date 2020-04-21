package com.zsh.restaurantsystem.repository;


import com.zsh.restaurantsystem.entity.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CustomizedRepoistory<Menu, Integer> {
}
