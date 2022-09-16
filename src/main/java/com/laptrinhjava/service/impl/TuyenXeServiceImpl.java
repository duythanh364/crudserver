package com.laptrinhjava.service.impl;

import com.laptrinhjava.model.TuyenXe;
import com.laptrinhjava.repository.IChuyenXeRepository;
import com.laptrinhjava.repository.ITuyenXeRepository;
import com.laptrinhjava.service.ITuyenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuyenXeServiceImpl implements ITuyenXeService {
    @Autowired
    ITuyenXeRepository tuyenXeRepo ;

    @Autowired
    IChuyenXeRepository chuyenXeRepo;
    @Override
    public List<TuyenXe> getAll() {
        return tuyenXeRepo.findAll();
    }

    @Override
    public TuyenXe getByID(int id) {
        return tuyenXeRepo.findById(id).get();
    }

    @Override
    public TuyenXe create(TuyenXe t) {
        try {
            return tuyenXeRepo.save(t);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public TuyenXe update(TuyenXe t) {
        try {
            TuyenXe v = tuyenXeRepo.findById(t.getId()).get();
            v.setDiemDau(t.getDiemDau());
            v.setDiemCuoi(t.getDiemCuoi());
            v.setDoDai(t.getDoDai());
            v.setDoPhucTap(t.getDoPhucTap());
            return tuyenXeRepo.save(v);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try {
            chuyenXeRepo.deleteByTuyenXeID(id);
            tuyenXeRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<TuyenXe> searchByDiemDauCuoi(String diemDau, String diemCuoi) {
        if( diemDau.trim().equals("") && !diemCuoi.trim().equals("")) {
            return tuyenXeRepo.findByDiemCuoi(diemCuoi);
        }
        else if( !diemDau.trim().equals("") && diemCuoi.trim().equals("")) {
            return tuyenXeRepo.findByDiemDau(diemDau);
        }
        else if(!diemDau.trim().equals("") && !diemCuoi.trim().equals("")) {

            return tuyenXeRepo.findByDiemDauAndDiemCuoi(diemDau, diemCuoi);
        }
        return tuyenXeRepo.findAll();
    }
}
