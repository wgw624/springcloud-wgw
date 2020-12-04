package com.wgw.entity;

import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author weiguangwei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "zth",type="t_shangpin")
public class Shop implements Serializable {

    @Id
    private String id;

    @Field(type= FieldType.Text,analyzer = "ik_max_word")
    private String shopName;

    private Long reducePrice;
}
