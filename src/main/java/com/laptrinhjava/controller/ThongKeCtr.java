package com.laptrinhjava.controller;

import com.laptrinhjava.model.ChuyenXe;
import com.laptrinhjava.service.ITaiXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/thongke", produces = "application/json")
public class ThongKeCtr {
    @Autowired
    ITaiXeService taiXeService;

    @GetMapping("/luongthang/{month}/{year}")
    public Map<Integer,Double> getLuongThangNay(@PathVariable("month") int month, @PathVariable("year") int year ){
        return taiXeService.getLuongThang(month, year);
    }
    @GetMapping("/sochuyenlaixe/{month}/{year}")
    public Map<Integer,Integer> getSoChuyenLaiXeTrongThang(@PathVariable("month") int month, @PathVariable("year") int year){
        return taiXeService.getSoChuyenLaiXeTrongThang(month,year);
    }

    @GetMapping("/sochuyenphuxe/{month}/{year}")
    public Map<Integer,Integer> getSoChuyenPhuXeTrongThang(@PathVariable("month") int month, @PathVariable("year") int year){
        return taiXeService.getSoChuyenPhuXeTrongThang(month,year);
    }
    @GetMapping("/chuyendachay/{month}/{year}")
    public Map<String, List<ChuyenXe>> getChuyenDaChay(@PathVariable("month") int month,@PathVariable("year") int year ){
        return taiXeService.getChuyenDaChay(month,year);
    }
}
