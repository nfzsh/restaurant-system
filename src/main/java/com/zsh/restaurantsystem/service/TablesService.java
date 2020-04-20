package com.zsh.restaurantsystem.service;

import com.zsh.restaurantsystem.entity.Tables;
import com.zsh.restaurantsystem.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    public int setTables(Tables tables) {
        if (tablesRepository.findNo(tables.getNo()) == null){
            tablesRepository.save(tables);
            return 1;
        }
        else
            return 0;

    }

    public void deleteTables(int id) {
        tablesRepository.deleteById(id);
    }

    public void updateTables(Tables tables) {
        tablesRepository.save(tables);
    }

    public Optional<Tables> getTables(Tables tables) {
        return tablesRepository.findById(tables.getId());
    }
    public List<Tables> getAll(){
        return tablesRepository.findAll();
    }

}
