package com.zsh.restaurantsystem.service;

import com.zsh.restaurantsystem.entity.Menu;
import com.zsh.restaurantsystem.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public int setMenu(Menu menu){
        if(menu.getId()==0) {
            menuRepository.save(menu);
            return 1;
        }
        else
            return 0;
    }

    public void deleteMenu(Menu menu){
        menuRepository.deleteById(menu.getId());
    }

    public int updateMenu(Menu menu){
        if(menu.getId()!=0) {
            menuRepository.save(menu);
            return 1;
        }
        else
            return 0;
    }

    public Optional<Menu> getMenu(Menu menu){
        return menuRepository.findById(menu.getId());
    }

    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }
}
