package com.xuchao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Computer {
    private Integer id;
    private Double price;
    private String screen;
    private String keyboard;
    private String mouse;
    private String color;
    private String klang;
    private Brand brand;
}
