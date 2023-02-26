package com.xuchao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuchao.entity.Computer;
import com.xuchao.entity.SearchName;
import com.xuchao.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ComputerController {
    @Autowired
    private ComputerService computerService;
    @GetMapping("/GET/computer/{pageNum}/{pageSize}")
    public PageInfo<Computer> selectAllComputer(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Computer> computers = computerService.selectAllComputer();
        PageInfo<Computer> page = new PageInfo<>(computers);
        return page;
    }

    @DeleteMapping("/DELETE/computer/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        computerService.deleteById(id);
    }

    @GetMapping("/GET/computer/{id}")
    public Computer selectComputerById(@PathVariable("id")Integer id){
        return computerService.selectComputerById(id);
    }

    @PutMapping("PUT/computer")
    public void updateComputer(@RequestBody Computer computer){
        computerService.updateComputer(computer);
    }
    @PostMapping("/POST/computer/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        computerService.deleteBatch(ids);
    }
    @PostMapping("/POST/computer")
    public void addComputer(@RequestBody Computer computer){
        computerService.addComputer(computer);
    }
    @RequestMapping("/POST/search/{pageNum}/{pageSize}")
    public PageInfo<Computer> search(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody SearchName searchName){
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> computers = computerService.searchByKlang(pageNum,pageSize,searchName.getContent());
        PageInfo<Computer> page = new PageInfo<>((ArrayList<Computer>)computers.get("computers"));
        page.setPageSize(pageSize);
        page.setPageNum(pageNum);
        page.setTotal((Long) computers.get("total"));

        return page;
    }
}
