package com.laptrinhjava.repository;

import com.laptrinhjava.model.TuyenXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITuyenXeRepository extends JpaRepository<TuyenXe, Integer> {
    @Query("Select tuyenXe from TuyenXe tuyenXe where tuyenXe.diemDau like %?1%")
    List<TuyenXe> findByDiemDau(String key);

    @Query("Select tuyenXe from TuyenXe tuyenXe where tuyenXe.diemCuoi like %?1%")
    List<TuyenXe> findByDiemCuoi(String key);

    @Query("Select tuyenXe from TuyenXe tuyenXe where tuyenXe.diemDau like %?1% and tuyenXe.diemCuoi like %?2%")
    List<TuyenXe> findByDiemDauAndDiemCuoi(String diemDau, String diemCuoi);

}
