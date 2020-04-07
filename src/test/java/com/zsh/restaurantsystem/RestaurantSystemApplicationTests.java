package com.zsh.restaurantsystem;

import com.zsh.restaurantsystem.entity.User;
import org.junit.jupiter.api.Test;
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
    @Test
    public void contextLoads() {
        var birth = LocalDate.of(1998, Month.MARCH,8);
        User user = new User(123,"zsh",birth,"4008823823");
        em.persist(user);
    }

}
