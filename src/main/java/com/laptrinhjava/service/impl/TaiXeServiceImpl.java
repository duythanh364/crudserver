package com.laptrinhjava.service.impl;

import com.laptrinhjava.model.ChuyenXe;
import com.laptrinhjava.model.TaiXe;
import com.laptrinhjava.modelReq.TaiXeReq;
import com.laptrinhjava.modelRes.LuongRes;
import com.laptrinhjava.modelRes.TaiXeRes;
import com.laptrinhjava.repository.IChuyenXeRepository;
import com.laptrinhjava.repository.ILuongRepository;
import com.laptrinhjava.repository.ITaiXeRepository;
import com.laptrinhjava.service.ITaiXeService;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service

public class TaiXeServiceImpl implements ITaiXeService {
    @Autowired
    ITaiXeRepository taiXeRepo;
    @Autowired
    IChuyenXeRepository chuyenXeRepo;
    @Autowired
    ILuongRepository luongRepo;

    @Autowired
    private final ModelMapper mapper;

    public TaiXeServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TaiXeRes> getAll() {
        List<TaiXe> listOld = taiXeRepo.findAll();
        List<TaiXeRes> listRes= new ArrayList<>();
        for(TaiXe t : listOld){
            listRes.add(mapper.map(t,TaiXeRes.class));
        }
        return listRes;
    }

    @Override
    public TaiXeRes getById(int id) {
        TaiXe tx= taiXeRepo.findById(id).get();

        return mapper.map(tx,TaiXeRes.class);
    }

    @Override
    public List<TaiXe> getByLuong(int id) {
        return taiXeRepo.findByIDLuong(id);
    }

    @Override
    public TaiXeRes create(TaiXeReq taixe) {
        try {
            TaiXe t = mapper.map(taixe,TaiXe.class);
            t.setLuong(luongRepo.getById(taixe.getLuongId()));
            System.out.println(t);
            return mapper.map(taiXeRepo.save(t),TaiXeRes.class);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public TaiXeRes update(TaiXeReq taixe, int id) {
        try {
            TaiXe t = taiXeRepo.findById(id).get();
            t= mapper.map(taixe,TaiXe.class);
            t.setId(id);
            t.setLuong(luongRepo.getById(taixe.getLuongId()));
            System.out.println(t);
            return mapper.map(taiXeRepo.save(t),TaiXeRes.class);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            taiXeRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<TaiXe> search(String type, String key) {
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

    @Override
    public List<TaiXe> sort(String sort__type, List<TaiXe> list) {
//        List<TaiXe> list = Arrays.asList(rest.getForObject("https://transportnhom14server.herokuapp.com/api/taixe",TaiXe[].class));
        if (sort__type.equals("ten")) {
            list.sort(Comparator.comparing((TaiXe taiXe) -> taiXe.getTen()));
        }
        if (sort__type.equals("thamNien")) {
            list.sort(Comparator.comparing((TaiXe taiXe) -> taiXe.getThamNien()));
        }
        return list;
    }

    @Override
    public Map<Integer, Double> getLuongThang(int month, int year) {
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

    @Override
    public Map<Integer, Integer> getSoChuyenLaiXeTrongThang(int month, int year) {
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

    @Override
    public Map<Integer, Integer> getSoChuyenPhuXeTrongThang(int month, int year) {
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

    @Override
    public Map<String, List<ChuyenXe>> getChuyenDaChay(int month, int year) {
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
