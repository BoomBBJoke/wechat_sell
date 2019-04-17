package com.xmcc.wechat_sell.service;

import com.xmcc.wechat_sell.common.ResultEnums;
import com.xmcc.wechat_sell.common.ResultResponse;
import com.xmcc.wechat_sell.dto.ProductCategoryDto;
import com.xmcc.wechat_sell.dto.ProductInfoDto;
import com.xmcc.wechat_sell.entity.ProductInfo;
import com.xmcc.wechat_sell.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ProductInfoRepository infoRepository;
    
    @Override
    public ResultResponse queryList() {
        ResultResponse<List<ProductCategoryDto>> categoryServiceResult = categoryService.findAll();
        List<ProductCategoryDto> categoryDtos = categoryServiceResult.getData();
        if (CollectionUtils.isEmpty(categoryDtos)){
            return categoryServiceResult;
        }
        //获得类目编号集合
        List<Integer> categoryTypeList = categoryDtos.stream().map(categorydto ->
                categorydto.getCategoryType()).collect(Collectors.toList());
        //根据type和status查询商品详情
        List<ProductInfo> productInfoList = infoRepository.findByProductStatusAndCategoryTypeIn(ResultEnums.PRODUCT_UP.getCode(), categoryTypeList);
        //多线程遍历  取出每个商品类目编号对应的商品列表 设置进入类目中
        List<ProductCategoryDto> finalResultList = categoryDtos.parallelStream().map(categorydto ->
        {
            categorydto.setProductInfoDtoList(
                    productInfoList.stream().
                    filter(productInfo -> productInfo.getCategoryType() == categorydto.getCategoryType()).map(ProductInfoDto::build).collect(Collectors.toList()));
            return categorydto;
        }).collect(Collectors.toList());
        return ResultResponse.success(finalResultList);
    }
}



