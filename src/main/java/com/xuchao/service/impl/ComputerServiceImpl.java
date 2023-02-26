package com.xuchao.service.impl;


import com.xuchao.dao.ComputerEsDao;
import com.xuchao.entity.Computer;
import com.xuchao.mapper.ComputerMapper;
import com.xuchao.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    private ComputerMapper computerMapper;
    @Autowired
    private ComputerEsDao computerEsDao;

    @Override
    @Transactional(readOnly = true)
    public List<Computer> selectAllComputer() {
        return computerMapper.selectAllComputer();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String,Object> searchByKlang(Integer pageNum, Integer pageSize, String content) {
        return computerEsDao.searchByKlang(pageNum,pageSize,content);
    }

    @Override
    public void deleteById(Integer id) {
        computerMapper.deleteById(id);
        computerEsDao.deleteById(id);
        System.err.println(id+"删除成功");

    }

    @Override
    @Transactional(readOnly = true)
    public Computer selectComputerById(Integer id) {
        return computerMapper.selectComputerById(id);
    }

    @Override
    public void updateComputer(Computer computer) {
        computerMapper.updateComputer(computer);
        computerEsDao.updateComputer(computer);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        computerMapper.deleteBatch(ids);
        computerEsDao.deleteBatch(ids);
        for (Integer id : ids) {
            System.err.println("删除成功");
        }

    }

    @Override
    public void addComputer(Computer computer) {
        //返回插入时的主键ID
        computerMapper.addComputer(computer);
        computerEsDao.insert(computer);
    }


}
