package com.laptrinhjava.service;

import com.laptrinhjava.model.ChuyenXe;
import com.laptrinhjava.model.TaiXe;
import com.laptrinhjava.modelReq.TaiXeReq;
import com.laptrinhjava.modelRes.TaiXeRes;

import java.util.List;
import java.util.Map;

public interface ITaiXeService {
    public List<TaiXeRes> getAll();
    public TaiXeRes getById(int id);
    public List<TaiXe> getByLuong(int id);
    public TaiXeRes create(TaiXeReq taixe);
    public TaiXeRes update(TaiXeReq taixe, int id);
    public void deleteById(int id);
    List<TaiXe> search(String type, String key);
    List<TaiXe> sort(String type, List<TaiXe> list);
    public Map<Integer,Double> getLuongThang(int month, int year );
    public Map<Integer,Integer> getSoChuyenLaiXeTrongThang(int month, int year);
    public Map<Integer,Integer> getSoChuyenPhuXeTrongThang(int month, int year);
    public Map<String, List<ChuyenXe>> getChuyenDaChay(int month, int year );
}
