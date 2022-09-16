package com.laptrinhjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="taixe")
@Data
public class TaiXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ten;
    @Column(unique = true)
    private String CMT;
    @Column(unique = true)
    private String maSoBangLai;
    private String loaiBangLai;
    private String diaChi;
    private Date ngaySinh;
    private int thamNien;

    @ManyToOne(targetEntity = Luong.class)
    @JoinColumn(name="luong_id")
    private Luong luong;
//    @OneToMany(mappedBy = "laiXe",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<ChuyenXe> chuyenXe1;
//    @OneToMany(mappedBy = "phuXe")
//    private List<ChuyenXe> chuyenXe2;
}
