package com.zsh.restaurantsystem.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class List {
    public static final int DISH_STATUE_W = 0;
    public static final int DISH_STATUE_R = 1;
    public static final int DISH_STATUE_O = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    User user;
    @ManyToOne
    Admin admin;
    @ManyToOne
    Table table;
    @ManyToOne
    Menu menu;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
           updatable = false,
           insertable = false)
    private LocalDateTime addTime;
    //未上菜为0，上菜为1，取消为2
    private int statue = 0;

    public List(User user, Admin admin, Table table, Menu menu, int statue) {
        this.user = user;
        this.admin = admin;
        this.table = table;
        this.menu = menu;
        this.statue = statue;
    }
}
