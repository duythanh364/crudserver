package com.laptrinhjava.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Luong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private int heSoLuong;
    private double luongCoBan;
    private double luongMoiChuyen;
}
