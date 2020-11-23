package com.wgw.controller;

import com.wgw.dao.EsDao;
import com.wgw.entity.Shop;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiguangwei
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private EsDao esDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/add")
    public void addShop(){
        Shop shop =new Shop();
        shop.setId("1");
        shop.setShopName("美食汇");
        shop.setReducePrice(100L);
        esDao.save(shop);
    }

    @RequestMapping("/query")
    public void query(){
        SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch("zth").setTypes("t_shangpin");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        searchRequestBuilder.setQuery(boolQueryBuilder);
//        设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("shopName").preTags("<font color='red'>").preTags("<font>");
        searchRequestBuilder.highlighter(highlightBuilder);

        SearchResponse searchResponse = searchRequestBuilder.get();
        System.out.println(searchResponse.getHits());
    }

}
