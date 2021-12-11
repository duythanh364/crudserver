package com.laptrinhjava.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="chuyenxe")
@Data
public class ChuyenXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int soKhach;

    private double giaVe;

    private Date ngayKhoiHanh;
    @ManyToOne(targetEntity = TaiXe.class)
    @JoinColumn(name="lai_xe_id")
    private TaiXe laiXe;

    @ManyToOne(targetEntity = TaiXe.class)
    @JoinColumn(name="phu_xe_id")
    private TaiXe phuXe;

    @ManyToOne(targetEntity = TuyenXe.class)
    @JoinColumn(name="tuyen_xe_id")
    private TuyenXe tuyenXe;

    @ManyToOne(targetEntity = XeKhach.class)
    @JoinColumn(name="xe_khach_id")
    private XeKhach xeKhach;
}
