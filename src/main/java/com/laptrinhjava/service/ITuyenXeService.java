package com.laptrinhjava.service;

import com.laptrinhjava.model.TuyenXe;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ITuyenXeService {
    public List<TuyenXe> getAll();
    public TuyenXe getByID(int id);
    public TuyenXe create( TuyenXe t);
    public TuyenXe update(TuyenXe t);
    public void delete(int id);
    public List<TuyenXe> searchByDiemDauCuoi(String diemDau,String diemCuoi);
}
