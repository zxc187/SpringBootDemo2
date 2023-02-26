package com.xuchao.service;

import com.xuchao.entity.Computer;

import java.util.List;
import java.util.Map;

public interface ComputerService {
    List<Computer> selectAllComputer();

    void deleteById(Integer id);

    Computer selectComputerById(Integer id);

    void updateComputer(Computer computer);

    void deleteBatch(Integer[] ids);

    void addComputer(Computer computer);


    Map<String,Object> searchByKlang(Integer pageNum, Integer pageSize, String content);
}
