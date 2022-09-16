package com.laptrinhjava.modelRes;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class LuongRes {
    private int id;
    private int heSoLuong;
    private double luongCoBan;
    private double luongMoiChuyen;
}
