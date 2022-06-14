package com.zwt.controller;


import com.zwt.controller.viewobject.ItemVO;
import com.zwt.error.BusinessException;
import com.zwt.response.CommonReturnType;
import com.zwt.service.ItemService;
import com.zwt.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
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
        ItemModel createdModel =  itemService.createItem(itemModel);

        ItemVO itemVO = convertItemVOFromModel(createdModel);
        return CommonReturnType.create(itemVO);
    }

    private ItemVO convertItemVOFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        return itemVO;
    }
}
