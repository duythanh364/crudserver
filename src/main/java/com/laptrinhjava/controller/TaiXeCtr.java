package com.laptrinhjava.controller;

import com.laptrinhjava.model.TaiXe;
import com.laptrinhjava.repository.ITaiXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="/api/taixe", produces = "application/json")
public class TaiXeCtr {
    @Autowired
    ITaiXeRepository taiXeRepo;

    @GetMapping
    public List<TaiXe> getAll(){
        return taiXeRepo.findAll();
    }

    @GetMapping(path="/{id}")
    public TaiXe getById(@PathVariable int id){
        return taiXeRepo.findById(id).get();
    }
    //lay nhung tai xe co bac luong id = id
    @GetMapping(path="/luong/{id}")
    public List<TaiXe> getByLuong(@PathVariable int id){
        return taiXeRepo.findByIDLuong(id);
    }

    @PostMapping
    public TaiXe insert(@RequestBody TaiXe taixe){
        try {
            return taiXeRepo.save(taixe);
        } catch (DataIntegrityViolationException e) {

            return null;
        }

    }

    @PutMapping
    public TaiXe put(@RequestBody TaiXe taixe){
        try {
            TaiXe t = taiXeRepo.findById(taixe.getId()).get();
            t.setCMT(taixe.getCMT());
            t.setDiaChi(taixe.getDiaChi());
            t.setLoaiBangLai(taixe.getLoaiBangLai());
            t.setLuong(taixe.getLuong());
            t.setMaSoBangLai(taixe.getMaSoBangLai());
            t.setNgaySinh(taixe.getNgaySinh());
            t.setTen(taixe.getTen());
            t.setThamNien(taixe.getThamNien());
            return taiXeRepo.save(t);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }
    @DeleteMapping(path="/{id}")
    public void delete(@PathVariable int id){
        try {
            taiXeRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
    }
    @GetMapping(path="/search/{search__type}/{key}")
    public List<TaiXe> search(@PathVariable("search__type") String type, @PathVariable("key") String key){
        List<TaiXe> list = new ArrayList<>();
        switch (type){
            case "ten": {
                list = taiXeRepo.findAllByTenContaining(key);
                break;
            }
            case "CMT": {
                TaiXe t = taiXeRepo.findByCMT(key);
                list.add(t);
                break;
            }
            case "diaChi": {
                list = taiXeRepo.findAllByDiaChi(key);
                break;
            }
        }
        return list;
    }

}
