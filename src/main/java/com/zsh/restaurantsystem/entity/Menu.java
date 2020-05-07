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
    private String pic ="";
    private float price;
    private String name;
    private int type;             //菜品类别 0主食 1菜品 2特色菜 3饮料 4酒水
    private int num;              //-1为不限量
    private String message;       //简介

    public Menu(String pic, float price, String name, int num) {
        this.pic = pic;
        this.price = price;
        this.name = name;
        this.num = num;
    }

}
