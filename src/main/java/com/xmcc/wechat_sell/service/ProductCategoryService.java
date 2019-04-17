package com.xmcc.wechat_sell.service;


import com.xmcc.wechat_sell.common.ResultResponse;
import com.xmcc.wechat_sell.dto.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryService  {

    ResultResponse<List<ProductCategoryDto>> findAll();

}
