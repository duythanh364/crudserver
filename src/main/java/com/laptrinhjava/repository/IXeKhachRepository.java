package com.laptrinhjava.repository;

import com.laptrinhjava.model.XeKhach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IXeKhachRepository extends JpaRepository<XeKhach,Integer> {
    @Query("select xeKhach from XeKhach xeKhach where xeKhach.bienSo like %?1%")
    List<XeKhach> findAllByBienSo(String bienSo);
    @Query("select xeKhach from XeKhach xeKhach where xeKhach.doiXe like %?1%")
    List<XeKhach> findAllByDoiXe(String doiXe);
    @Query("select xeKhach from XeKhach xeKhach where xeKhach.hangSX like %?1%")
    List<XeKhach> findAllByHangSX(String hangSX);
    @Query("select xeKhach from XeKhach xeKhach where xeKhach.mauXe like %?1%")
    List<XeKhach> findAllByMauXe(String mauXe);
    @Query("select xeKhach from XeKhach xeKhach where xeKhach.model like %?1%")
    List<XeKhach> findAllByModel(String model);
    List<XeKhach> findAllBySoGhe(int soGhe);
    List<XeKhach> findAllBySoNamSD(int soNamSD);
    List<XeKhach> findAllByNgayBaoDuongCuoiCungBetween(Date batDau, Date ketThuc);
}
