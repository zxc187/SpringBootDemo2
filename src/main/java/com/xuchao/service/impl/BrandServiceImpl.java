package com.xuchao.service.impl;

import com.xuchao.entity.Brand;
import com.xuchao.mapper.BrandMapper;
import com.xuchao.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Brand> selectAllBrand() {
        return brandMapper.selectAllBrand();
    }
}
