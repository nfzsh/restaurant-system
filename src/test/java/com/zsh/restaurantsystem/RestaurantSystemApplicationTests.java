package com.zsh.restaurantsystem;

import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RestaurantSystemApplicationTests {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private AdminRepository adminRepository;
    @Test
    public void contextLoads() {

        adminRepository.deleteById(adminRepository.findId("zsh"));
    }


}
