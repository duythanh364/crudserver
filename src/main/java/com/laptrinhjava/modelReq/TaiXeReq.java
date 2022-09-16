package com.laptrinhjava.modelReq;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laptrinhjava.model.Luong;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
public class TaiXeReq {
    private int id;
    private String ten;
    private String CMT;
    private String maSoBangLai;
    private String loaiBangLai;
    private String diaChi;
    private Date ngaySinh;
    private int thamNien;
    private int luongId;
}
