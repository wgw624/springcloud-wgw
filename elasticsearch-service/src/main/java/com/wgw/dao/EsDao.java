package com.wgw.dao;

import com.wgw.entity.Shop;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface EsDao extends ElasticsearchCrudRepository<Shop,String> {
}
