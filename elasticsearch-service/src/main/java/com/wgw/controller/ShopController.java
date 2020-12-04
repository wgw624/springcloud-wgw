package com.wgw.controller;

import com.wgw.dao.EsDao;
import com.wgw.entity.Shop;
import com.wgw.mapper.ShopRespository;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author weiguangwei
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private EsDao esDao;

    @Autowired
    private ShopRespository shopRespository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/add")
    public void addShop(Shop shop){
//        Shop shop =new Shop();
        if(ObjectUtils.isEmpty(shop)){
            shop = new Shop();
            shop.setId("中华人民共和国");
            shop.setShopName("美食汇");
            shop.setReducePrice(100L);

        }
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

    @RequestMapping("queryTest")
    public void queryTest(){
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("shopName","牛肉");
        System.out.println("MatchQueryBuilder");
        Iterable<Shop> shops = shopRespository.search(matchQueryBuilder);
        shops.forEach(sp->{
            System.out.println(sp.getId()+"-->"+sp.getShopName()+"--->"+sp.getReducePrice());
        });
        Optional<Shop> shop0 = esDao.findById("2");
        System.out.println(shop0.get());;
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("shopName","牛");
        System.out.println("TermQueryBuilder");
        Iterable<Shop> shop1 = shopRespository.search(termQueryBuilder);
        shop1.forEach(sp->{
            System.out.println(sp.getId()+"---->"+sp.getShopName()+"--->"+sp.getReducePrice());
        });
        QueryBuilders.multiMatchQuery("shopName","牛肉","reducePrice","18L");
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("shopName","肉");
        Iterable<Shop> shop2 = shopRespository.search(fuzzyQueryBuilder);
        System.out.print("模糊查询----");
        shop2.forEach(sp->{
            System.out.println("FuzzyQueryBuilder"+sp);
        });

        WildcardQueryBuilder query = QueryBuilders.wildcardQuery("shopName","肉*");
        Iterable<Shop>shop3 = shopRespository.search(query);
        shop3.forEach(sp->{
            System.out.println("通配符WildcardQueryBuilder"+sp);
        });

        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("shopName","牛肉");
        Iterable<Shop>shop4 = shopRespository.search(prefixQueryBuilder);
        shop4.forEach(sp->{
            System.out.println("前缀 prefixQueryBuilder"+sp);
        });

        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("shopName","牛肉");
        Iterable<Shop>shop5 = shopRespository.search(matchPhraseQueryBuilder);
        System.out.print("matchPhraseQueryBuilder----");
        shop5.forEach(sp->{
            System.out.println("matchPhraseQueryBuilder"+sp);
        });
    }
    @RequestMapping("queryById")
    public Shop queryById(String shopId){
//        List<Shop> list= Collections.EMPTY_LIST;
        if(StringUtils.isEmpty(shopId)){
            Iterable<Shop> shops = esDao.findAll();
            shops.forEach(sp->{
                System.out.println(sp.getShopName()+"-->"+sp.getReducePrice());
            });
            return null;
        }else{
            Optional<Shop> shop = esDao.findById(shopId);
            return shop.get();

        }


    }


}
