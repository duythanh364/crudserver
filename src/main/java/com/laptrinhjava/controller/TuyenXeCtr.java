package com.laptrinhjava.controller;

import com.laptrinhjava.model.TuyenXe;
import com.laptrinhjava.repository.IChuyenXeRepository;
import com.laptrinhjava.repository.ITuyenXeRepository;
import java.util.ArrayList;

import com.laptrinhjava.service.ITuyenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/tuyenxe")
public class TuyenXeCtr {
    @Autowired
    ITuyenXeService tuyenXeService;

    @GetMapping
    public List<TuyenXe> getAll(){

        return tuyenXeService.getAll();
    }

    @GetMapping(path="/{id}")
    public TuyenXe getByID(@PathVariable int id){
        return tuyenXeService.getByID(id);
    }

    @PostMapping
    public TuyenXe insert(@RequestBody TuyenXe t){
        return tuyenXeService.create(t);
    }

    @PutMapping
    public TuyenXe update(@RequestBody TuyenXe t){
        return  tuyenXeService.update(t);
    }

    @DeleteMapping(path="/{id}")
    public void delete(@PathVariable int id){
        tuyenXeService.delete(id);
    }

    @GetMapping(path="/search/{diemDau}/{diemCuoi}")
    public List<TuyenXe> searchByDiemDauCuoi(@PathVariable String diemDau, @PathVariable String diemCuoi){
        return tuyenXeService.searchByDiemDauCuoi(diemDau,diemCuoi);
    }
}
