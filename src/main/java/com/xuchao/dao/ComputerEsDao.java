package com.xuchao.dao;

import com.xuchao.entity.Computer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public interface ComputerEsDao {
    Map<String,Object> searchByKlang(Integer pageNum, Integer pageSize, String content);
    void insert(Computer computer);
    void deleteById(Integer id);
    void updateComputer(Computer computer);
    void deleteBatch(Integer[] ids);
}
