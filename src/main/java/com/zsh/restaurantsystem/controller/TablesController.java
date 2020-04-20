package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Tables;
import com.zsh.restaurantsystem.service.TablesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin/table")
public class TablesController {

    @Autowired
    TablesService tablesService;

    @PostMapping("/add")
    public Map setTable(@RequestBody Tables tables, HttpServletResponse response) {
        //1保存成功0重复no
        return Map.of("message",tablesService.setTables(tables));
    }

    @PostMapping("/delete")
    public void deleteTable(@RequestBody Tables tables, HttpServletResponse response) {
        tablesService.deleteTables(tables.getId());
    }

    @PostMapping("/update")
    public Map updateTable(@RequestBody Tables tables, HttpServletResponse response) {
        if (tables.getId()==0)
            return Map.of("message",0);
        else {
            tablesService.updateTables(tables);
            return Map.of("message",1);
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
