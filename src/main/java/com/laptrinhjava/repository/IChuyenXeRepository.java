package com.laptrinhjava.repository;

import com.laptrinhjava.model.ChuyenXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface IChuyenXeRepository extends JpaRepository<ChuyenXe,Integer> {
    @Query("Select u from ChuyenXe u where u.ngayKhoiHanh>=?1 and u.ngayKhoiHanh<= ?2 ORDER BY u.ngayKhoiHanh")
    List<ChuyenXe> findByNgayKhoiHanhBetweenOrder(Date start, Date end);
    @Query("Select u from ChuyenXe u where u.laiXe.id=?1 or u.phuXe.id= ?1")
    List<ChuyenXe> findByIDTaiXe(int id);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.laiXe.id=?1")
    List<ChuyenXe> findByLaiXeID(int id);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.phuXe.id=?1")
    List<ChuyenXe> findByPhuXeID(int id);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.xeKhach.id=?1")
    List<ChuyenXe> findByXeKhachID(int id);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.tuyenXe.id=?1")
    List<ChuyenXe> findByTuyenXeID(int id);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.phuXe.ten like %?1%")
    List<ChuyenXe> findByPhuXeTen(String key);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.laiXe.ten like %?1%")
    List<ChuyenXe> findByLaiXeTen(String key);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.tuyenXe.diemDau like %?1%")
    List<ChuyenXe> findByDiemDauTuyenXe(String key);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.tuyenXe.diemCuoi like %?1%")
    List<ChuyenXe> findByDiemCuoiTuyenXe(String key);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.tuyenXe.diemDau like %?1% and chuyenXe.tuyenXe.diemCuoi like %?2%")
    List<ChuyenXe> findByTuyenXe(String key1,String key2);

    List<ChuyenXe> findByNgayKhoiHanh(java.util.Date date);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.phuXe.CMT like %?1%")
    List<ChuyenXe> findByPhuXeCMT(String key);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.laiXe.CMT like %?1%")
    List<ChuyenXe> findByLaiXeCMT(String key);

    @Query("select chuyenXe from ChuyenXe chuyenXe where chuyenXe.xeKhach.bienSo like %?1%")
    List<ChuyenXe> findByXeKhach(String key);

    @Transactional
    @Modifying
    @Query("delete from ChuyenXe chuyenXe where chuyenXe.xeKhach.id=?1")
    void deleteByXeKhachID(int id);

    @Transactional
    @Modifying
    @Query("delete from ChuyenXe chuyenXe where chuyenXe.tuyenXe.id=?1")
    void deleteByTuyenXeID(int id);

    List<ChuyenXe> findAllByNgayKhoiHanhBetween(Date batDau,Date ketThuc);
}
