package com.laptrinhjava.service.impl;

import com.laptrinhjava.model.Luong;
import com.laptrinhjava.modelRes.LuongRes;
import com.laptrinhjava.repository.ILuongRepository;
import com.laptrinhjava.service.IBacLuongService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LuongServiceImpl implements IBacLuongService {
    @Autowired
    ILuongRepository luongRepo;
    @Autowired
    private final ModelMapper mapper;

    public LuongServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<LuongRes> getAll() {
        List<Luong> old = luongRepo.findAll();
        List<LuongRes> res= new ArrayList<>();
        for(Luong l : old){
            res.add(mapper.map(l,LuongRes.class));
        }
        return res;
    }

    @Override
    public Luong getByID(int id) {
        return luongRepo.findById(id).get();
    }

    @Override
    public Luong create(Luong t) {
        try {
            return luongRepo.save(t);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

    }

    @Override
    public Luong update(Luong luong) {
        try {
            Luong l = luongRepo.findById(luong.getId()).get();
            l.setLuongCoBan(luong.getLuongCoBan());
            l.setHeSoLuong(luong.getHeSoLuong());
            l.setLuongMoiChuyen(luong.getLuongMoiChuyen());
            return luongRepo.save(l);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try {
            luongRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
    }
}
