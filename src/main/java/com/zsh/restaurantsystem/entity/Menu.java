package com.zsh.restaurantsystem.entity;

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
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pic;
    private float price;
    private String name;
    private int type;
    private int num;//-1为不限量

    public Menu(String pic, float price, String name, int num) {
        this.pic = pic;
        this.price = price;
        this.name = name;
        this.num = num;
    }

}
