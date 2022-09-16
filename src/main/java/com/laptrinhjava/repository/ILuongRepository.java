package com.laptrinhjava.repository;

import com.laptrinhjava.model.Luong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


public interface ILuongRepository extends JpaRepository<Luong,Integer> {
}
