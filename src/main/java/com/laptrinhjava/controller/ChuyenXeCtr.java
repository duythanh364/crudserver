package com.laptrinhjava.controller;
import com.laptrinhjava.model.ChuyenXe;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import com.laptrinhjava.service.IChuyenXeService;


@RestController
@RequestMapping(path="/api/chuyenxe")
public class ChuyenXeCtr {
    @Autowired
    IChuyenXeService chuyenXeImpl;

    @GetMapping
    public List<ChuyenXe> getAll(){
        return chuyenXeImpl.getAll();
    }

    @GetMapping(path="/{id}")
    public ChuyenXe getById(@PathVariable int id){
        return chuyenXeImpl.getById(id);
    }

    // lấy những chuyến xe mà có tài xế có id này tham gia
    @GetMapping(path="/taixe/{id}")
    public List<ChuyenXe> getByTaiXe(@PathVariable int id){
        return chuyenXeImpl.getByTaiXe(id);
    }

    @GetMapping("/{start}/{end}")
    public List<ChuyenXe> getAllBetween(@PathVariable("start") Date start, @PathVariable("end") Date end) throws ParseException {
            return chuyenXeImpl.getAllBetween(start, end);
    }

    @PostMapping
    public ChuyenXe insert(@RequestBody ChuyenXe chuyenxe) {
            return chuyenXeImpl.insert(chuyenxe);
    }

    @PutMapping
    public ChuyenXe update(@RequestBody ChuyenXe chuyenxe) {
            return chuyenXeImpl.update(chuyenxe);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
         chuyenXeImpl.delete(id);


    }

    @GetMapping("/search/{search__type}/{keyword}")
    public List<ChuyenXe> getSearch(@PathVariable("search__type") String search__type, @PathVariable("keyword") String keyword) {
        return chuyenXeImpl.getSearch(search__type, keyword);
    }

}