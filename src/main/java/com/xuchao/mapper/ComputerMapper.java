package com.xuchao.mapper;

import com.xuchao.entity.Computer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ComputerMapper {
    List<Computer> selectAllComputer();

    void deleteById(Integer id);

    Computer selectComputerById(Integer id);

    void updateComputer(Computer computer);

    void deleteBatch(@Param("ids")Integer[] ids);

    void addComputer(Computer computer);
}
