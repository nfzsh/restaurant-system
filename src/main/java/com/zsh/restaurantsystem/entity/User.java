package com.zsh.restaurantsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int openid;
    private String name;
    private LocalDate birth;
    private String phone_num;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;

    public User(int openid) {
        this.openid = openid;
    }

    public User(int openid, String name, LocalDate birth,
                String phone_num) {
        this.openid = openid;
        this.name = name;
        this.birth = birth;
        this.phone_num = phone_num;
    }
}
