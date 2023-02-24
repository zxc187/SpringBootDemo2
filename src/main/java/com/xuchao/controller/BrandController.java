package com.xuchao.controller;

import com.xuchao.entity.Brand;
import com.xuchao.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("/GET/brand")
    public List<Brand> selectAllBrand(){
        return brandService.selectAllBrand();
    }
}
