package com.laptrinhjava.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tuyenxe")
@Data
public class TuyenXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String diemDau;
    private String diemCuoi;
    private int doDai;
    private int doPhucTap;
}
