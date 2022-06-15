package com.zwt.service;

import com.zwt.utils.error.BusinessException;
import com.zwt.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItemById(Integer id);

    //扣减库存Decrease
    boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException;

    //增加销量
    boolean increaseSales(Integer itemId, Integer amount);

}
