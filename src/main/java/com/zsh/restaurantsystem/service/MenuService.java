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

    public List<Menu> deleteMenu(int mid){
        int type = menuRepository.findById(mid).get().getType();
        menuRepository.deleteById(mid);
        return menuRepository.findMenusByType(type);
    }

    public List<Menu> updateMenu(Menu menu){
        menu.setPic(menuRepository.findById(menu.getId()).get().getPic());
        menuRepository.save(menu);
        return menuRepository.findMenusByType(menu.getType());
    }

    public Optional<Menu> getMenu(Menu menu){
        return menuRepository.findById(menu.getId());
    }
    public Menu getMenuById(int id){
        return menuRepository.findById(id).get();
    }

    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }
    public List<Menu> getMenuByType(int type){
        return menuRepository.findMenusByType(type);
    }
}
