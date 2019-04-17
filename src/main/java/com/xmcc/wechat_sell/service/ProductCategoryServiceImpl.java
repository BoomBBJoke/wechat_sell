package com.xmcc.wechat_sell.service;

import com.xmcc.wechat_sell.common.ResultResponse;
import com.xmcc.wechat_sell.dto.ProductCategoryDto;
import com.xmcc.wechat_sell.entity.ProductCategory;
import com.xmcc.wechat_sell.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository categoryRepository;
    
    
    @Override
    public ResultResponse<List<ProductCategoryDto>> findAll() {
        List<ProductCategory> categoryList = categoryRepository.findAll();
        List<ProductCategoryDto> categoryDtoList
                = categoryList.stream().map(productCategory -> ProductCategoryDto
                .build(productCategory)).collect(Collectors.toList());
        return ResultResponse.success(categoryDtoList);
    }
}
