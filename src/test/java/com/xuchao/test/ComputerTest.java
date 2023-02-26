package com.xuchao.test;


import com.xuchao.Application;
import com.xuchao.entity.Brand;
import com.xuchao.entity.Computer;
import com.xuchao.mapper.BrandMapper;
import com.xuchao.mapper.ComputerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ComputerTest {
    @Autowired
    private ComputerMapper computerMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Test
    public void test1(){
        System.err.println(computerMapper.selectAllComputer());
    }
    @Test
    public void updateComputer(){
        Computer computer = new Computer(2,111.1,"111","222","555","111","123",new Brand(2,"dsa"));
        computerMapper.updateComputer(computer);
    }
    @Test
    public void addComputer(){
        Computer computer = new Computer(2,111.1,"111","222","555","111","123",new Brand(2,"dsa"));
        computerMapper.addComputer(computer);
    }
    @Test
    public  void test2(){
        Brand brand = brandMapper.selectById(1);
        System.out.println(brand);
        System.err.println(brand);
    }
}
