package com.xuchao.dao.impl;

import com.xuchao.dao.ComputerEsDao;
import com.xuchao.entity.Brand;
import com.xuchao.entity.Computer;
import com.xuchao.mapper.BrandMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class ComputerEsDaoImpl implements ComputerEsDao {
    @Autowired
    private TransportClient client;
    @Autowired
    private BrandMapper brandMapper;


    @Override
    public Map<String,Object> searchByKlang(Integer pageNum, Integer pageSize, String content) {
        Map<String,Object> map = new HashMap<>();
        SearchResponse searchResponse = client.prepareSearch("springbootdemo2").setTypes("computer")
                .setQuery(QueryBuilders.multiMatchQuery(content, "klang", "screen","mouse","color","keyboard"))
                .setFrom((pageNum-1)*pageSize).setSize(pageSize)
                .addSort("price", SortOrder.ASC).highlighter(new HighlightBuilder().field("content").field("screen")
                        .field("mouse").field("color").field("keyboard").preTags("<span style='color:red'>").postTags("</span>"))
                .get();

        SearchHits hits = searchResponse.getHits();
        map.put("total",hits.getTotalHits());
        System.err.println(hits.getTotalHits());
        List<Computer> computers = new ArrayList<>();

        for (SearchHit hit:hits){
            Computer computer = new Computer();
            Brand brand = new Brand();
            //获取原始数据
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            //获取高亮显示结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            //设置computer对象的id
            computer.setId(Integer.parseInt(hit.getId()));
            //设置computer对象的price
            computer.setPrice((Double) sourceAsMap.get("price"));
            //设置computer对象的brand
            Map<String, Object> brandMap = (Map)sourceAsMap.get("brand");
            brand.setBrand_id((Integer) brandMap.get("brand_id"));
            brand.setBrand_name((String) brandMap.get("brand_name"));
            computer.setBrand(brand);

            if(highlightFields.get("klang") != null){
                computer.setKlang(highlightFields.get("klang").fragments()[0].toString());
            }else{
                computer.setKlang(sourceAsMap.get("klang").toString());
            }

            if(highlightFields.get("screen") != null){
                computer.setScreen(highlightFields.get("screen").fragments()[0].toString());
            }else{
                computer.setScreen(sourceAsMap.get("screen").toString());
            }
            if(highlightFields.get("mouse") != null){
                computer.setMouse(highlightFields.get("mouse").fragments()[0].toString());
            }else{
                computer.setMouse(sourceAsMap.get("mouse").toString());
            }
            if(highlightFields.get("color") != null){
                computer.setColor(highlightFields.get("color").fragments()[0].toString());
            }else{
                computer.setColor(sourceAsMap.get("color").toString());
            }
            if(highlightFields.get("keyboard") != null){
                computer.setKlang(highlightFields.get("keyboard").fragments()[0].toString());
            }else{
                computer.setKeyboard(sourceAsMap.get("keyboard").toString());
            }
            computers.add(computer);
        }
        map.put("computers",computers);
        return map;
    }

    @Override
    public void insert(Computer computer) {
        Map map=new HashMap();
        //根据品牌ID获取品牌名字
        Brand brand = brandMapper.selectById(computer.getBrand().getBrand_id());

        //放入品牌Map的数据
        Map brandMap = new HashMap();
        brandMap.put("brand_id",brand.getBrand_id());
        brandMap.put("brand_name",brand.getBrand_name());

        map.put("price",computer.getPrice());
        map.put("screen",computer.getScreen());
        map.put("keyboard",computer.getKeyboard());
        map.put("mouse",computer.getMouse());
        map.put("color",computer.getColor());
        map.put("klang",computer.getKlang());
        map.put("brand",brandMap);

        //执行插入
        IndexResponse indexResponse = client.prepareIndex("springbootdemo2", "computer", computer.getId().toString())
                .setSource(map).get();
    }

    @Override
    public void deleteById(Integer id) {
        //执行删除
        DeleteResponse deleteResponse = client.prepareDelete("springbootdemo2", "computer", id.toString()).get();
    }

    @Override
    public void updateComputer(Computer computer) {
        //准备数据
        Map map=new HashMap();
        Map brandMap = new HashMap();
        Brand brand = brandMapper.selectById(computer.getBrand().getBrand_id());
        brandMap.put("brand_id",brand.getBrand_id());
        brandMap.put("brand_name",brand.getBrand_name());
        map.put("brand",brandMap);
        map.put("price",computer.getPrice());
        map.put("screen",computer.getScreen());
        map.put("keyboard",computer.getKeyboard());
        map.put("mouse",computer.getMouse());
        map.put("color",computer.getColor());
        map.put("klang",computer.getKlang());

        //执行修改
        UpdateResponse updateResponse = client.prepareUpdate("springbootdemo2", "computer", computer.getId().toString())
                .setDoc(map).get();
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        for (Integer id : ids) {
            DeleteResponse deleteResponse = client.prepareDelete("springbootdemo2", "computer",id.toString()).get();
        }
    }

}
