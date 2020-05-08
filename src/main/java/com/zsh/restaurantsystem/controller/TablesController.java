package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Tables;
import com.zsh.restaurantsystem.service.TablesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin/table")
public class TablesController {

    @Autowired
    TablesService tablesService;

    @PostMapping("/add")
    public Map setTable(@RequestBody Tables tables) {
        tablesService.setTables(tables);
        return Map.of("tables",tablesService.getAll());
    }

    @GetMapping("/delete/{tid}")
    public Map deleteTable(@PathVariable int tid, HttpServletResponse response) {
        tablesService.deleteTables(tid);
        return Map.of("tables",tablesService.getAll());
    }

    @PostMapping("/update")
    public Map updateTable(@RequestBody Tables tables) {
        if (tables.getId()==0)
            return Map.of("tables",tablesService.getAll(),"message","更新失败");
        else {
            tablesService.updateTables(tables);
            return Map.of("tables",tablesService.getAll(),"message","更新成功");
        }
    }

    @PostMapping("/select")
    public Map getTable(@RequestBody Tables tables,HttpServletResponse response){
        return Map.of("table",tablesService.getTables(tables));
    }
    @GetMapping("/select_all")
    public Map getAll(){
        return Map.of("tables",tablesService.getAll());
    }
}
