package com.moti.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        // 定义客户端配置对象 9200端口
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.30.132:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
