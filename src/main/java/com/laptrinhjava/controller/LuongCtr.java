package com.laptrinhjava.controller;


import com.laptrinhjava.model.Luong;
import com.laptrinhjava.modelRes.LuongRes;
import com.laptrinhjava.service.IBacLuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/bacluong", produces = "application/json")
@CrossOrigin("http://localhost:3000")
public class LuongCtr {
    @Autowired
    IBacLuongService bacLuongService;

    @GetMapping
    public List<LuongRes> getAll(){
       return bacLuongService.getAll();
    }

    @GetMapping(path="/{id}")
    public Luong getById(@PathVariable int id){
        return bacLuongService.getByID(id);
    }
    @PostMapping
    public Luong insert(@RequestBody Luong luong){
        return bacLuongService.create(luong);
    }
    @PutMapping()
    public Luong update(@RequestBody Luong luong){
        return bacLuongService.update(luong);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        bacLuongService.delete(id);
    }
}
