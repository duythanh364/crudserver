package com.laptrinhjava.controller;


import com.laptrinhjava.model.Luong;
import com.laptrinhjava.repository.ILuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/bacluong", produces = "application/json")
public class LuongCtr {
    @Autowired
    ILuongRepository luongRepo;

    @GetMapping
    public List<Luong> getAll(){
        return luongRepo.findAll();
    }

    @GetMapping(path="/{id}")
    public Luong getById(@PathVariable int id){
        return luongRepo.findById(id).get();
    }
    @PostMapping
    public Luong insert(@RequestBody Luong luong){
        try {
            return luongRepo.save(luong);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }
    @PutMapping()
    public Luong update(@RequestBody Luong luong){
        try {
            Luong l = luongRepo.findById(luong.getId()).get();
            l.setLuongCoBan(luong.getLuongCoBan());
            l.setHeSoLuong(luong.getHeSoLuong());
            l.setLuongMoiChuyen(luong.getLuongMoiChuyen());
            return luongRepo.save(l);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        try {
            luongRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }

    }
}
