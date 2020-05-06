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
    private String openid;
    private String session_key;
    private String name;
    private String avatarUrl;
    private LocalDate birth;
    private String phone_num;
    private String gender;
    private String province;
    private String city;
    private String country;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;

//    public User(int id) {
//        this.id = id;
//    }

//    public User(int openid, String name, LocalDate birth,
//                String phone_num) {
//        this.openid = openid;
//        this.name = name;
//        this.birth = birth;
//        this.phone_num = phone_num;
//    }
}
