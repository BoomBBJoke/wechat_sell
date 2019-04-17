package com.xmcc.wechat_sell.repository;

import com.xmcc.wechat_sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatusAndCategoryTypeIn(Integer status, List<Integer> categoryList);


}
