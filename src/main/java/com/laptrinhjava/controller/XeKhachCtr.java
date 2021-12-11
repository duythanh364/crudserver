package com.laptrinhjava.controller;

import com.laptrinhjava.model.XeKhach;
import com.laptrinhjava.repository.IChuyenXeRepository;
import com.laptrinhjava.repository.IXeKhachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api/xekhach", produces = "application/json")
public class XeKhachCtr {

    @Autowired
    IXeKhachRepository xeKhachRepo;
    @Autowired
    IChuyenXeRepository chuyenXeRepo;

    @GetMapping
    public List<XeKhach> getAll() {
        return xeKhachRepo.findAll();
    }

    @GetMapping("/{id}")
    public XeKhach getByID(@PathVariable int id) {
        try{
            return xeKhachRepo.findById(id).get();
        } catch(NoSuchElementException e){
            return null;
        }
    }

    @PostMapping()
    public XeKhach insert(@RequestBody XeKhach xeKhach) {
        try {
            return xeKhachRepo.save(xeKhach);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @PutMapping
    public XeKhach update(@RequestBody XeKhach xe) {
        try {
            XeKhach a = xeKhachRepo.findById(xe.getId()).get();
            a.setBienSo(xe.getBienSo());
            a.setDoiXe(xe.getDoiXe());
            a.setHangSX(xe.getHangSX());
            a.setModel(xe.getModel());
            a.setMauXe(xe.getMauXe());
            a.setNgayBaoDuongCuoiCung(xe.getNgayBaoDuongCuoiCung());
            a.setSoGhe(xe.getSoGhe());
            a.setSoNamSD(xe.getSoNamSD());

            return xeKhachRepo.save(a);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
        try {
            chuyenXeRepo.deleteByXeKhachID(id);
            xeKhachRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }

    }

    @GetMapping("/search/{searchType}/{keyword}")
    public List<XeKhach> getSearch(@PathVariable("searchType") String searchType
            , @PathVariable("keyword") String keyword){
        if(searchType.equals("bienSo")){
            try{
                return  xeKhachRepo.findAllByBienSo(keyword);
            }
            catch(Exception e){
                return null;
            }
        }
        if(searchType.equals("doiXe")){
            try{
                return  xeKhachRepo.findAllByDoiXe(keyword);
            }
            catch(Exception e){
                return null;
            }
        }
        if(searchType.equals("hangSX")){
            try{
                return  xeKhachRepo.findAllByHangSX(keyword);
            }
            catch(Exception e){
                return null;
            }
        }
        if(searchType.equals("mauXe")){
            try{
                return  xeKhachRepo.findAllByMauXe(keyword);
            }
            catch(Exception e){
                return null;
            }
        }
        if(searchType.equals("model")){
            try{
                return  xeKhachRepo.findAllByModel(keyword);
            }
            catch(Exception e){
                return null;
            }
        }
        if(searchType.equals("soGhe")){
            try{
                int soGhe = Integer.parseInt(keyword);
                return  xeKhachRepo.findAllBySoGhe(soGhe);
            }
            catch(Exception e){
                return null;
            }
        }
        if(searchType.equals("soNamSD")){
            try{
                int soNamSD = Integer.parseInt(keyword);
                return  xeKhachRepo.findAllBySoNamSD(soNamSD);
            }
            catch(Exception e){
                return null;
            }
        }

        return null;
    }

}
