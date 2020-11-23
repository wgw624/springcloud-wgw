package com.wgw.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author weiguangwei
 */
@Data
@Document(indexName = "zth",type="t_shangpin")
public class Shop implements Serializable {

    private String id;

    @Field(type= FieldType.Text,analyzer = "ik_max_word")
    private String shopName;

    private Long reducePrice;
}
