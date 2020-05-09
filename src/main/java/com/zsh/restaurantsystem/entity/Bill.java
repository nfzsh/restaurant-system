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
public class Bill {
    public static final int BILL_STATUE_W = 0; //未支付
    public static final int BILL_STATUE_R = 1; //已支付
    public static final int BILL_STATUE_O = 2; //取消
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    float price;
    int statue = 0;
    private LocalDateTime payTime;
    private String note;

    public Bill(User user, float price, int statue, LocalDateTime payTime, String note) {
        this.user = user;
        this.price = price;
        this.statue = statue;
        this.payTime = payTime;
        this.note = note;
    }
}
