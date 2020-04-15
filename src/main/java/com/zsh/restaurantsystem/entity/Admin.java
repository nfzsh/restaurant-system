package com.zsh.restaurantsystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Admin {
    public static final int ADMIN_AUTHORITY = 0;
    public static final int USER1_AUTHORITY = 1;
    public static final int USER2_AUTHORITY = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    //默认为0
    private int authority = 0;

    public Admin(String name, String password, int authority) {
        this.name = name;
        this.password = password;
        this.authority = authority;
    }
}
