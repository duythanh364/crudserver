package com.laptrinhjava.controller;

import com.laptrinhjava.model.XeKhach;
import com.laptrinhjava.service.IXeKhachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "/api/xekhach", produces = "application/json")
public class XeKhachCtr {

    @Autowired
    IXeKhachService xeKhachService;

    @GetMapping
    public List<XeKhach> getAll() {
        return xeKhachService.getAll();
    }

    @GetMapping("/{id}")
    public XeKhach getByID(@PathVariable int id) {
        return xeKhachService.getByID(id);
    }

    @PostMapping()
    public XeKhach insert(@RequestBody XeKhach xeKhach) {
        return xeKhachService.create(xeKhach);
    }

    @PutMapping
    public XeKhach update(@RequestBody XeKhach xe) {
        return xeKhachService.update(xe);

    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id) {
        xeKhachService.delete(id);
    }

    @GetMapping("/search/{searchType}/{keyword}")
    public List<XeKhach> getSearch(@PathVariable("searchType") String searchType
            , @PathVariable("keyword") String keyword){
        return xeKhachService.search(searchType, keyword);
    }

}
