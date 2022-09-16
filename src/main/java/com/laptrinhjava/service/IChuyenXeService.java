package com.laptrinhjava.service;
import com.laptrinhjava.model.ChuyenXe;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
public interface IChuyenXeService {
    public List<ChuyenXe> getAll();

    public ChuyenXe getById(int id);

    public List<ChuyenXe> getByTaiXe( int id);

    public List<ChuyenXe> getAllBetween(Date start, Date end) throws ParseException;

    public ChuyenXe insert(ChuyenXe chuyenxe);

    public ChuyenXe update( ChuyenXe chuyenxe) ;

    public void delete( int id) ;

    public List<ChuyenXe> getSearch(String search__type,String keyword) ;

}