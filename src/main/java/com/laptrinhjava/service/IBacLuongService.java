package com.laptrinhjava.service;

import com.laptrinhjava.model.Luong;
import com.laptrinhjava.model.XeKhach;
import com.laptrinhjava.modelRes.LuongRes;

import java.util.List;

public interface IBacLuongService {
    public List<LuongRes> getAll();
    public Luong getByID(int id);
    public Luong create( Luong t);
    public Luong update( Luong t);
    public void delete(int id);
}
