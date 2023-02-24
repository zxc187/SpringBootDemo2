package com.xuchao.mapper;

import com.xuchao.entity.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {

    List<Brand> selectAllBrand();
}
