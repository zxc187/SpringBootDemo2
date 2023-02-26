package com.xuchao.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
@Configuration//表示这个类是配置类
public class TransportClientConfig {
    @PostConstruct
    public void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    @Bean
    public TransportClient getClient(){
        try {
            TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.111.60"), 9300));
            return transportClient;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
