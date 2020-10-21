package com.moti.elasticsearch;

import com.moti.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ClassName: ArticleRespository
 * @Description: TODO
 * @author: 莫提
 * @date 2020/10/9 19:42
 * @Version: 1.0
 **/
public interface ArticleRespository extends ElasticsearchRepository<Article,Integer> {
}
