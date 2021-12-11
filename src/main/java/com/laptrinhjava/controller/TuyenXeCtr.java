package com.laptrinhjava.controller;

import com.laptrinhjava.model.TuyenXe;
import com.laptrinhjava.repository.IChuyenXeRepository;
import com.laptrinhjava.repository.ITuyenXeRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/tuyenxe")
public class TuyenXeCtr {
    @Autowired
    ITuyenXeRepository tuyenXeRepo ;

    @Autowired
    IChuyenXeRepository chuyenXeRepo;

    @GetMapping
    public List<TuyenXe> getAll(){
        System.out.println(tuyenXeRepo.findAll());
        return tuyenXeRepo.findAll();
    }

    @GetMapping(path="/{id}")
    public TuyenXe getByID(@PathVariable int id){
        return tuyenXeRepo.findById(id).get();
    }

    @PostMapping
    public TuyenXe insert(@RequestBody TuyenXe t){
        try {
            return tuyenXeRepo.save(t);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }

    @PutMapping
    public TuyenXe update(@RequestBody TuyenXe t){
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

    @DeleteMapping(path="/{id}")
    public void delete(@PathVariable int id){
        try {
            chuyenXeRepo.deleteByTuyenXeID(id);
            tuyenXeRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(path="/search/{diemDau}/{diemCuoi}")
    public List<TuyenXe> searchByDiemDau(@PathVariable String diemDau, @PathVariable String diemCuoi){
        System.out.println("hi");
        if( diemDau.trim().equals("") && !diemCuoi.trim().equals("")) {
            return tuyenXeRepo.findByDiemCuoi(diemCuoi);
        }
        else if( !diemDau.trim().equals("") && diemCuoi.trim().equals("")) {
            return tuyenXeRepo.findByDiemDau(diemDau);
        }
        else if(!diemDau.trim().equals("") && !diemCuoi.trim().equals("")) {
            System.out.println("hihi");
            return tuyenXeRepo.findByDiemDauAndDiemCuoi(diemDau, diemCuoi);
        }
        return tuyenXeRepo.findAll();
    }
}
