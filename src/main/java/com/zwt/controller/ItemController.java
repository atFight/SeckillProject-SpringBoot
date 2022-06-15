package com.zwt.controller;


import com.zwt.controller.viewobject.ItemVO;
import com.zwt.error.BusinessException;
import com.zwt.response.CommonReturnType;
import com.zwt.service.ItemService;
import com.zwt.service.model.ItemModel;
import com.zwt.service.model.PromoModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true",origins = "*", allowedHeaders = "*")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam("title")String title,
                                       @RequestParam("price")BigDecimal price,
                                       @RequestParam("stock")Integer stock,
                                       @RequestParam("description")String description,
                                       @RequestParam("imgUrl")String imgUrl) throws BusinessException {

        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        ItemModel createdModel = itemService.createItem(itemModel);

        ItemVO itemVO = convertItemVOFromModel(createdModel);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam("id")Integer id) {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = convertItemVOFromModel(itemModel);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem() {
        List<ItemModel> modelList = itemService.listItem();

        List<ItemVO> itemVOList = modelList.stream().map(itemModel -> {
            return this.convertItemVOFromModel(itemModel);
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }

    private ItemVO convertItemVOFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() == null) {
            itemVO.setPromoStatus(PromoModel.PromoModelStatus.NONE);
        }else {
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVO.setPromoStartTime(itemModel.getPromoModel().getStartTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return itemVO;
    }
}
