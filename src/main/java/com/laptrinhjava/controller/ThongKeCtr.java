package com.laptrinhjava.controller;

import com.laptrinhjava.model.ChuyenXe;
import com.laptrinhjava.model.TaiXe;
import com.laptrinhjava.repository.IChuyenXeRepository;
import com.laptrinhjava.repository.ILuongRepository;
import com.laptrinhjava.repository.ITaiXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/thongke", produces = "application/json")
public class ThongKeCtr {
    @Autowired
    ITaiXeRepository taiXeRepo;

    @Autowired
    ILuongRepository luongRepo;

    @Autowired
    IChuyenXeRepository chuyenXeRepo;

    @GetMapping("/luongthang/{month}/{year}")
    public Map<Integer,Double> getLuongThangNay(@PathVariable("month") int month, @PathVariable("year") int year ){
        // Lấy ngày đầu tháng và cuối tháng
        LocalDate todaydate = LocalDate.now();
        // ngày đầu tháng todaydate.withdayofmonth(1)
        java.sql.Date start;
        java.sql.Date end;
        // neu la thang nay thi chi tinh den ngay hien tai
        if(month==todaydate.getMonthValue() && year==todaydate.getYear()){
            start = new java.sql.Date(todaydate.withDayOfMonth(1).getYear()-1900,
                    todaydate.withDayOfMonth(1).getMonthValue()-1,
                    todaydate.withDayOfMonth(1).getDayOfMonth());
            end= new java.sql.Date(todaydate.getYear()-1900,
                    todaydate.getMonthValue()-1,
                    todaydate.getDayOfMonth());
        }
        else{
            start = new java.sql.Date(year-1900, month-1, 1);
            end= new java.sql.Date(year-1900, month-1, 30);
        }
        System.out.println(start+"--"+end);
        Map<Integer,Double> mapLuong= new HashMap<>();
        List<TaiXe> list = taiXeRepo.findAll();
        List<ChuyenXe> listChuyen= chuyenXeRepo.findByNgayKhoiHanhBetweenOrder(start,end);
        for(TaiXe t : list){
            for(ChuyenXe c : listChuyen){
                if(c.getLaiXe().getId()==t.getId()){
                    if(mapLuong.containsKey(t.getId())){
                        mapLuong.put(t.getId(),mapLuong.get(t.getId())+
                                c.getTuyenXe().getDoPhucTap()*t.getLuong().getLuongMoiChuyen()*2);
                    }
                    else{
                        mapLuong.put(t.getId(),
                                c.getTuyenXe().getDoPhucTap()*t.getLuong().getLuongMoiChuyen()*2);
                    }
                }
                if(c.getPhuXe().getId()==t.getId()){
                    if(mapLuong.containsKey(t.getId())){
                        mapLuong.put(t.getId(),mapLuong.get(t.getId())+
                                c.getTuyenXe().getDoPhucTap()*t.getLuong().getLuongMoiChuyen());
                    }
                    else{
                        mapLuong.put(t.getId(),
                                c.getTuyenXe().getDoPhucTap()*t.getLuong().getLuongMoiChuyen());
                    }
                }
            }
            if(mapLuong.containsKey(t.getId()))
                mapLuong.put(t.getId(),mapLuong.get(t.getId())+
                        t.getLuong().getLuongCoBan()*t.getLuong().getHeSoLuong());
            else
                mapLuong.put(t.getId(),
                    t.getLuong().getLuongCoBan()*t.getLuong().getHeSoLuong());
        }
        return mapLuong;
    }
    @GetMapping("/sochuyenlaixe/{month}/{year}")
    public Map<Integer,Integer> getSoChuyenLaiXeTrongThang(@PathVariable("month") int month, @PathVariable("year") int year){
        // Lấy ngày đầu tháng và cuối tháng
        LocalDate todaydate = LocalDate.now();
        // ngày đầu tháng todaydate.withdayofmonth(1)
        java.sql.Date start;
        java.sql.Date end;
        // neu la thang nay thi chi tinh den ngay hien tai
        if(month==todaydate.getMonthValue() && year==todaydate.getYear()){
            start = new java.sql.Date(todaydate.withDayOfMonth(1).getYear()-1900,
                    todaydate.withDayOfMonth(1).getMonthValue()-1,
                    todaydate.withDayOfMonth(1).getDayOfMonth());
            end= new java.sql.Date(todaydate.getYear()-1900,
                    todaydate.getMonthValue()-1,
                    todaydate.getDayOfMonth());
        }
        else{
            start = new java.sql.Date(year-1900, month-1, 1);
            end= new java.sql.Date(year-1900, month-1, 30);
        }
        Map<Integer,Integer> mapLaiXe= new HashMap<>();
        List<TaiXe> list = taiXeRepo.findAll();
        List<ChuyenXe> listChuyen= chuyenXeRepo.findByNgayKhoiHanhBetweenOrder(start,end);
        for(TaiXe t : list){
            for(ChuyenXe c : listChuyen){
                if(c.getLaiXe().getId()==t.getId()){
                    if(mapLaiXe.containsKey(t.getId())){
                        mapLaiXe.put(t.getId(),mapLaiXe.get(t.getId())+1);
                    }
                    else{
                        mapLaiXe.put(t.getId(),1);
                    }
                }
            }

            if(mapLaiXe.get(t.getId())==null) mapLaiXe.put(t.getId(),0);
        }
        return mapLaiXe;
    }

    @GetMapping("/sochuyenphuxe/{month}/{year}")
    public Map<Integer,Integer> getSoChuyenPhuXeTrongThang(@PathVariable("month") int month, @PathVariable("year") int year){
        // Lấy ngày đầu tháng và cuối tháng
        LocalDate todaydate = LocalDate.now();
        // ngày đầu tháng todaydate.withdayofmonth(1)
        java.sql.Date start;
        java.sql.Date end;
        // neu la thang nay thi chi tinh den ngay hien tai
        if(month==todaydate.getMonthValue() && year==todaydate.getYear()){
            start = new java.sql.Date(todaydate.withDayOfMonth(1).getYear()-1900,
                    todaydate.withDayOfMonth(1).getMonthValue()-1,
                    todaydate.withDayOfMonth(1).getDayOfMonth());
            end= new java.sql.Date(todaydate.getYear()-1900,
                    todaydate.getMonthValue()-1,
                    todaydate.getDayOfMonth());
        }
        else{
            start = new java.sql.Date(year-1900, month-1, 1);
            end= new java.sql.Date(year-1900, month-1, 30);
        }

        Map<Integer,Integer> mapPhuXe= new HashMap<>();
        List<TaiXe> list = taiXeRepo.findAll();
        List<ChuyenXe> listChuyen= chuyenXeRepo.findByNgayKhoiHanhBetweenOrder(start,end);
        for(TaiXe t : list){
            for(ChuyenXe c : listChuyen){
                if(c.getPhuXe().getId()==t.getId()){
                    if(mapPhuXe.containsKey(t.getId())){
                        mapPhuXe.put(t.getId(),mapPhuXe.get(t.getId())+1);
                    }
                    else{
                        mapPhuXe.put(t.getId(),1);
                    }
                }
            }
            if(mapPhuXe.get(t.getId())==null) mapPhuXe.put(t.getId(),0);
        }
        return mapPhuXe;
    }
    @GetMapping("/chuyendachay/{month}/{year}")
    public Map<String, List<ChuyenXe>> getChuyenDaChay(@PathVariable("month") int month,@PathVariable("year") int year ){
        // Lấy ngày đầu tháng và cuối tháng
        LocalDate todaydate = LocalDate.now();
        // ngày đầu tháng todaydate.withdayofmonth(1)
        java.sql.Date start;
        java.sql.Date end;
        // neu la thang nay thi chi tinh den ngay hien tai
        if(month==todaydate.getMonthValue() && year==todaydate.getYear()){
            start = new java.sql.Date(todaydate.withDayOfMonth(1).getYear()-1900,
                    todaydate.withDayOfMonth(1).getMonthValue()-1,
                    todaydate.withDayOfMonth(1).getDayOfMonth());
            end= new java.sql.Date(todaydate.getYear()-1900,
                    todaydate.getMonthValue()-1,
                    todaydate.getDayOfMonth());
        }
        else{
            start = new java.sql.Date(year-1900, month-1, 1);
            end= new java.sql.Date(year-1900, month-1, 30);
        }

        List<ChuyenXe> listChuyen= chuyenXeRepo.findByNgayKhoiHanhBetweenOrder(start,end);
        Map<String,List<ChuyenXe>> mapChuyen= new HashMap<>();
        for(ChuyenXe c : listChuyen){
            if(mapChuyen.containsKey(c.getLaiXe().getTen())){
                List<ChuyenXe> l = new ArrayList<>();
                l=mapChuyen.get(c.getLaiXe().getTen());
                l.add(c);
                mapChuyen.put(c.getLaiXe().getTen(), l);
            }
            else{
                List<ChuyenXe> l = new ArrayList<>();
                l.add(c);
                mapChuyen.put(c.getLaiXe().getTen(), l);
            }
            if(mapChuyen.containsKey(c.getPhuXe().getTen())){
                List<ChuyenXe> l = new ArrayList<>();
                l=mapChuyen.get(c.getPhuXe().getTen());
                l.add(c);
                mapChuyen.put(c.getPhuXe().getTen(), l);
            }
            else{
                List<ChuyenXe> l = new ArrayList<>();
                l.add(c);
                mapChuyen.put(c.getPhuXe().getTen(), l);
            }
        }
        return mapChuyen;
    }
}
