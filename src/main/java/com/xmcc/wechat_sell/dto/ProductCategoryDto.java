package com.xmcc.wechat_sell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.wechat_sell.entity.ProductCategory;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductCategoryDto implements Serializable {
    /** 类目名字. */
    @JsonProperty("name")
    private String categoryName;

    /** 类目编号. */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
//防止为null时被忽略
    public List<ProductInfoDto> productInfoDtoList;

    //转换成Dto
    public static ProductCategoryDto build(ProductCategory productCategory){
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        BeanUtils.copyProperties(productCategory,productCategoryDto);
        return productCategoryDto;
    }

}
