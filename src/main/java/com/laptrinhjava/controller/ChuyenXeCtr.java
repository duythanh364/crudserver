package com.laptrinhjava.controller;
import com.laptrinhjava.model.ChuyenXe;
import com.laptrinhjava.model.XeKhach;
import com.laptrinhjava.repository.IChuyenXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path="/api/chuyenxe")
public class ChuyenXeCtr {
    @Autowired
    IChuyenXeRepository chuyenXeRepo;

    @GetMapping
    public List<ChuyenXe> getAll(){
        return chuyenXeRepo.findAll();
    }

    @GetMapping(path="/{id}")
    public ChuyenXe getById(@PathVariable int id){
        return chuyenXeRepo.findById(id).get();
    }

    // lấy những chuyến xe mà có tài xế có id này tham gia
    @GetMapping(path="/taixe/{id}")
    public List<ChuyenXe> getByTaiXe(@PathVariable int id){
        return chuyenXeRepo.findByIDTaiXe(id);
    }

    @GetMapping("/{start}/{end}")
    public List<ChuyenXe> getAllBetween(@PathVariable("start") Date start, @PathVariable("end") Date end) throws ParseException {
        try {
            return chuyenXeRepo.findAllByNgayKhoiHanhBetween(start, end);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @PostMapping
    public ChuyenXe insert(@RequestBody ChuyenXe chuyenxe) {
        try {
            return chuyenXeRepo.save(chuyenxe);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }

    @PutMapping
    public ChuyenXe update(@RequestBody ChuyenXe chuyenxe) {
        try {
            ChuyenXe c = chuyenXeRepo.findById(chuyenxe.getId()).get();
            c.setGiaVe(chuyenxe.getGiaVe());
            c.setLaiXe(chuyenxe.getLaiXe());
            c.setPhuXe(chuyenxe.getPhuXe());
            c.setSoKhach(chuyenxe.getSoKhach());
            c.setTuyenXe(chuyenxe.getTuyenXe());
            c.setXeKhach(chuyenxe.getXeKhach());
            c.setNgayKhoiHanh(chuyenxe.getNgayKhoiHanh()); //Thêm
            return chuyenXeRepo.save(c);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
        try {
            chuyenXeRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }

    }

    @GetMapping("/search/{search__type}/{keyword}")
    public List<ChuyenXe> getSearch(@PathVariable("search__type") String search__type,
                                    @PathVariable("keyword") String keyword) {

        if (search__type.equals("idTaiXe")) {
            try {
                int id = Integer.parseInt(keyword);
                return chuyenXeRepo.findByLaiXeID(id);
            } catch (Exception e) {
                return null;
            }
        }

        if (search__type.equals("idPhuXe")) {
            try {
                int id = Integer.parseInt(keyword);
                return chuyenXeRepo.findByPhuXeID(id);
            } catch (Exception e) {
                return null;
            }
        }
        if (search__type.equals("idXeKhach")) {
            try {
                int id = Integer.parseInt(keyword);
                return chuyenXeRepo.findByXeKhachID(id);
            } catch (Exception e) {
                return null;
            }
        }
        if (search__type.equals("idTuyenXe")) {
            try {
                int id = Integer.parseInt(keyword);
                return chuyenXeRepo.findByTuyenXeID(id);
            } catch (Exception e) {
                return null;
            }
        }
        if (search__type.equals("tenLaiXe")) {
            try {
                return chuyenXeRepo.findByLaiXeTen(keyword);
            } catch (Exception e) {
                return null;
            }
        }
        if (search__type.equals("tenPhuXe")) {
            try {
                return chuyenXeRepo.findByPhuXeTen(keyword);
            } catch (Exception e) {
                return null;
            }
        }
        if(search__type.equals("diemDau")){
            try {
                return chuyenXeRepo.findByDiemDauTuyenXe(keyword);
            } catch (Exception e) {
            }
        }
        if(search__type.equals("diemCuoi")){
            try {
                return chuyenXeRepo.findByDiemCuoiTuyenXe(keyword);
            } catch (Exception e) {
            }
        }
        if(search__type.equals("dauCuoi")){
            String dau="";
            String cuoi="";
            try {
                for(int i=0;i<keyword.length();i++){
                    if(keyword.charAt(i)=='/'||keyword.charAt(i)=='-'){
                        dau=keyword.substring(0, i);
                        cuoi=keyword.substring(i+1);
                    }
                }
                return chuyenXeRepo.findByTuyenXe(dau, cuoi);
            } catch (Exception e) {
            }
        }
        if(search__type.equals("ngayKhoiHanh")){
            try {
                return chuyenXeRepo.findByNgayKhoiHanh(new SimpleDateFormat("yyyy-MM-dd").parse(keyword));
            } catch (Exception e) {
            }
        }
        if(search__type.equals("cmtLaiXe")){
            try {
                return chuyenXeRepo.findByLaiXeCMT(keyword);
            } catch (Exception e) {
            }
        }
        if(search__type.equals("cmtPhuXe")){
            try {
                return chuyenXeRepo.findByPhuXeCMT(keyword);
            } catch (Exception e) {
            }
        }
        if(search__type.equals("bienSoXe")){
            try {
                return chuyenXeRepo.findByXeKhach(keyword);
            } catch (Exception e) {
            }
        }
        return null;

    }

}
