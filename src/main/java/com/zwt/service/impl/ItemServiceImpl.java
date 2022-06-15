package com.zwt.service.impl;

import com.zwt.dao.ItemDOMapper;
import com.zwt.dao.ItemStockDOMapper;
import com.zwt.dataobject.ItemDO;
import com.zwt.dataobject.ItemStockDO;
import com.zwt.error.BusinessException;
import com.zwt.error.EmBusinessError;
import com.zwt.service.ItemService;
import com.zwt.service.PromoService;
import com.zwt.service.model.ItemModel;
import com.zwt.service.model.PromoModel;
import com.zwt.validator.ValidationResult;
import com.zwt.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Autowired
    private PromoService promoService;

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        //校验入参
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidationResult validationResult = validator.validate(itemModel);
        if (validationResult.isHasError()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getAllErrorMsg());
        }
        //转化对象 写入数据库
        ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);
        itemDOMapper.insertSelective(itemDO);

        itemModel.setId(itemDO.getId());
        ItemStockDO itemStockDO = this.convertItemStockDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        //返回结果
        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> list = itemDOMapper.listItem();

        //java8 stream方法修改List内容并返回一个全新的list
        List<ItemModel> modelList = list.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            return this.convertModelFromDataObject(itemDO, itemStockDO);
        }).collect(Collectors.toList());

        return modelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null) {
            return null;
        }
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(id);
        if (itemStockDO == null) {
            return null;
        }

        ItemModel itemModel =this.convertModelFromDataObject(itemDO, itemStockDO);

        PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
        if (promoModel != null && promoModel.getStatus() != PromoModel.PromoModelStatus.ENDED) {
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int effectRow = itemStockDOMapper.decreaseStock(itemId, amount);
        return effectRow > 0;
    }

    @Override
    @Transactional
    public boolean increaseSales(Integer itemId, Integer amount) {
        int effectRow = itemDOMapper.increaseSales(itemId, amount);
        return effectRow > 0;
    }


    private ItemModel convertModelFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setPrice(BigDecimal.valueOf(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }

    private ItemDO convertItemDOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }

    private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setStock(itemModel.getStock());
        itemStockDO.setItemId(itemModel.getId());
        return itemStockDO;
    }
}
