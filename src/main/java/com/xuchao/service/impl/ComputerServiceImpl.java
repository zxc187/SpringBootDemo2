package com.xuchao.service.impl;


import com.xuchao.entity.Computer;
import com.xuchao.mapper.ComputerMapper;
import com.xuchao.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    private ComputerMapper computerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Computer> selectAllComputer() {
        return computerMapper.selectAllComputer();
    }

    @Override
    public void deleteById(Integer id) {
        computerMapper.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Computer selectComputerById(Integer id) {
        return computerMapper.selectComputerById(id);
    }

    @Override
    public void updateComputer(Computer computer) {
        computerMapper.updateComputer(computer);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        computerMapper.deleteBatch(ids);
    }

    @Override
    public void addComputer(Computer computer) {
        computerMapper.addComputer(computer);
    }
}
