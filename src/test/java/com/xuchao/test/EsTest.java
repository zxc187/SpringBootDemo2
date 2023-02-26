package com.xuchao.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuchao.Application;
import com.xuchao.dao.ComputerEsDao;
import com.xuchao.dao.impl.ComputerEsDaoImpl;
import com.xuchao.entity.Computer;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EsTest {
    @Autowired
    private TransportClient client;
    @Autowired
    private ComputerEsDao computerEsDao;
    @Test
    public void test1(){
        SearchResponse searchResponse = client.prepareSearch("ems").setTypes("emp")
                .setQuery(QueryBuilders.multiMatchQuery("张黑java", "name", "content"))
                .setFrom(2).setSize(3)
                .addSort("age", SortOrder.ASC)
                .get();
    }
    @Test
    public void test2(){
        SearchResponse searchResponse = client.prepareSearch("springbootdemo2").setTypes("computer")
                .setQuery(QueryBuilders.multiMatchQuery("中华", "klang", "screen","mouse","color","keyboard"))
                .setFrom(0).setSize(5)
                .addSort("price", SortOrder.ASC).highlighter(new HighlightBuilder().field("content").field("screen")
                        .field("mouse").field("color").field("keyboard").preTags("<span style='color:red'>").postTags("</span>"))
                .get();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit:hits){
            //获取原始数据
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.err.println(hit.getId());

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            //获取高亮显示结果
            System.err.println(highlightFields);
//            HighlightField content = highlightFields.get("screen");
//
//            if(highlightFields.get("screen")!=null){
//                Text fragment = content.fragments()[0];
//                System.out.println(fragment);
//            }
        }
    }

    @Test
    public void test3(){
        PageHelper.startPage(2,1);
        Map<String,Object> computers = computerEsDao.searchByKlang(2,1,"中华");
        PageInfo<Computer> page = new PageInfo<>((ArrayList<Computer>)computers.get(computers));
        System.out.println(page);
    }
}
