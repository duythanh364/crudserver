package com.laptrinhjava.controller;

import com.laptrinhjava.model.TaiXe;

import com.laptrinhjava.modelReq.TaiXeReq;
import com.laptrinhjava.modelRes.TaiXeRes;
import com.laptrinhjava.service.ITaiXeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/taixe", produces = "application/json")
@CrossOrigin("http://localhost:3000")

public class TaiXeCtr {
    @Autowired
    ITaiXeService taiXeService;

    @GetMapping
    public List<TaiXeRes> getAll(){
        return taiXeService.getAll();
    }

    @GetMapping(path="/{id}")
    public TaiXeRes getById(@PathVariable int id){
        return taiXeService.getById(id);
    }
    //lay nhung tai xe co bac luong id = id
    @GetMapping(path="/luong/{id}")
    public List<TaiXe> getByLuong(@PathVariable int id){
        return taiXeService.getByLuong(id);
    }

    @PostMapping
    public TaiXeRes insert(@RequestBody TaiXeReq taixe){
        return taiXeService.create(taixe);
    }

    @PutMapping(path="/{id}")
    public TaiXeRes put(@RequestBody TaiXeReq taixe, @PathVariable int id){
        System.out.println(taixe);
        System.out.println(id);
        return taiXeService.update(taixe,id);
    }
    @DeleteMapping(path="/{id}")
    public void delete(@PathVariable int id){
        taiXeService.deleteById(id);
    }
    @GetMapping(path="/search/{search__type}/{key}")
    public List<TaiXe> search(@PathVariable("search__type") String type, @PathVariable("key") String key){
        return taiXeService.search(type,key);
    }
}
