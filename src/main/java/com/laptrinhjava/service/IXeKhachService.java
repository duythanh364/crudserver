package com.laptrinhjava.service;

import com.laptrinhjava.model.TuyenXe;
import com.laptrinhjava.model.XeKhach;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IXeKhachService {
    public List<XeKhach> getAll();
    public XeKhach getByID(int id);
    public XeKhach create( XeKhach t);
    public XeKhach update( XeKhach t);
    public void delete(int id);
    public List<XeKhach> search(String searchType, String keyword);
}
