package com.wgw.mapper;

import com.wgw.entity.Shop;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program: springcloud-wgw
 * @description: 商店
 * @author: wgw
 * @date: 2020/12/3 1:43 下午
 **/
public interface ShopRespository extends ElasticsearchRepository<Shop,String> {
}
