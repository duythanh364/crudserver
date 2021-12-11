package com.laptrinhjava.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="xekhach")
@Data
public class XeKhach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String bienSo;
    private String mauXe;
    private String hangSX;
    private String model;
    private String doiXe;
    private int soGhe;
    private int soNamSD;
    private Date ngayBaoDuongCuoiCung;
}
