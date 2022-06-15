package com.zwt.service.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class ItemModel {

    private Integer id;

    @NotBlank(message = "请输入商品名称")
    private String title;

    @NotNull(message = "请输入商品价格")
    @Min(value = 0, message = "商品价格必须大于0元")
    private BigDecimal price;

    @NotNull(message = "请输入商品库存")
    private Integer stock;

    @NotBlank(message = "请输入商品描述")
    private String description;

    private Integer sales;

    //商品描述图片
    @NotBlank(message = "商品图片信息不能为空")
    private String imgUrl;

    private PromoModel promoModel;
}
