package com.laptrinhjava.service.impl;

import com.laptrinhjava.model.ChuyenXe;
import com.laptrinhjava.repository.IChuyenXeRepository;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.laptrinhjava.service.IChuyenXeService;

@Service
public class ChuyenXeServiceImpl implements IChuyenXeService{

    @Autowired
    IChuyenXeRepository chuyenXeRepo;

    public List<ChuyenXe> getAll(){
        return chuyenXeRepo.findAll();
    }

    @Override
    public ChuyenXe getById( int id){
        return chuyenXeRepo.findById(id).get();
    }

    // lấy những chuyến xe mà có tài xế có id này tham gia
    @Override
    public List<ChuyenXe> getByTaiXe( int id){
        return chuyenXeRepo.findByIDTaiXe(id);
    }

    @Override
    public List<ChuyenXe> getAllBetween(Date start,Date end) throws ParseException {
        try {
            return chuyenXeRepo.findAllByNgayKhoiHanhBetween(start, end);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public ChuyenXe insert(ChuyenXe chuyenxe) {
        try {
            return chuyenXeRepo.save(chuyenxe);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }

    @Override
    public ChuyenXe update(ChuyenXe chuyenxe) {
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

    @Override
    public void delete(int id) {
        try {
            chuyenXeRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<ChuyenXe> getSearch(String search__type,String keyword) {

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