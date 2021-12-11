package com.laptrinhjava.repository;

import com.laptrinhjava.model.ChuyenXe;
import com.laptrinhjava.model.TaiXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaiXeRepository extends JpaRepository<TaiXe,Integer> {
    List<TaiXe> findAllByTenContaining(String ten);
    TaiXe findByCMT(String CMT);
    List<TaiXe> findAllByDiaChi(String diaChi);
    @Query("Select u from TaiXe u where u.luong.id=?1")
    List<TaiXe> findByIDLuong(int id);
}
