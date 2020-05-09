package com.zsh.restaurantsystem;

import com.zsh.restaurantsystem.entity.Bill;
import com.zsh.restaurantsystem.entity.TableNum;
import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.repository.AdminRepository;
import com.zsh.restaurantsystem.repository.BillRepository;
import com.zsh.restaurantsystem.repository.ListRepository;
import com.zsh.restaurantsystem.repository.TablesRepository;
import com.zsh.restaurantsystem.service.TablesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RestaurantSystemApplicationTests {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private BillRepository repository;
    @Test
    public void contextLoads() {
        Bill b = new Bill();
        b.setPrice(11);
        repository.save(b);
        System.out.println(b.getId());
    }


}
