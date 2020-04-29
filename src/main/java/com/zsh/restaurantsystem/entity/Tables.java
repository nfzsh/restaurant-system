package com.zsh.restaurantsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Tables {
    private static final int STATUE_ON = 1;
    private static final int STATUE_OFF = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int no;
    private int num;
    //默认为空 0
    private int statue = 0;
    private LocalDateTime beginTime;
    private LocalDateTime finishTime;

    public Tables(int no, int num, int statue) {
        this.no = no;
        this.num = num;
        this.statue = statue;
    }

}
