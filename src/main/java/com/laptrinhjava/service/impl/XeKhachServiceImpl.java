package com.laptrinhjava.service.impl;

import com.laptrinhjava.model.XeKhach;
import com.laptrinhjava.repository.IChuyenXeRepository;
import com.laptrinhjava.repository.IXeKhachRepository;
import com.laptrinhjava.service.IXeKhachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class XeKhachServiceImpl implements IXeKhachService {
    @Autowired
    IXeKhachRepository xeKhachRepo;
    @Autowired
    IChuyenXeRepository chuyenXeRepo;
    @Override
    public List<XeKhach> getAll() {
        return xeKhachRepo.findAll();
    }

    @Override
    public XeKhach getByID(int id) {
        try{
            return xeKhachRepo.findById(id).get();
        } catch(NoSuchElementException e){
            return null;
        }
    }

    @Override
    public XeKhach create(XeKhach t) {
        try {
            return xeKhachRepo.save(t);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public XeKhach update(XeKhach xe) {
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

    @Override
    public void delete(int id) {
        try {
            chuyenXeRepo.deleteByXeKhachID(id);
            xeKhachRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<XeKhach> search(String searchType, String keyword) {
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
