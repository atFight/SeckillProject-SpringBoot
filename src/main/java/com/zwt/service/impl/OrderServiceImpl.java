package com.zwt.service.impl;

import com.zwt.dao.OrderDOMapper;
import com.zwt.dao.SequenceDOMapper;
import com.zwt.dataobject.OrderDO;
import com.zwt.dataobject.SequenceDO;
import com.zwt.error.BusinessException;
import com.zwt.error.EmBusinessError;
import com.zwt.service.ItemService;
import com.zwt.service.OrderService;
import com.zwt.service.UserService;
import com.zwt.service.model.ItemModel;
import com.zwt.service.model.OrderModel;
import com.zwt.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDER_INFO_TABLE = "order_info";
    private static final Integer SEQUENCE_MAX_LENGTH = 6;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }

        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不正确");
        }

        if (amount <= 0 || amount >= 99) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "请输入正确购买数量");
        }

        //落单减库存
        boolean isSuccess = itemService.decreaseStock(itemId, amount);
        if (!isSuccess) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        //订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setAmount(amount);
        orderModel.setOrderPrice(itemModel.getPrice().multiply(BigDecimal.valueOf(amount)));
        orderModel.setId(this.generateOrderNo());

        OrderDO orderDO = convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        //增加销量
        itemService.increaseSales(itemId, amount);
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNo() {
        StringBuilder stringBuilder = new StringBuilder();

        LocalDateTime localDateTime = LocalDateTime.now();
        String timeStr = localDateTime.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(timeStr);

        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName(ORDER_INFO_TABLE);
        String seqNo = String.valueOf(sequenceDO.getCurrentValue());
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        for (int i = 0; i < SEQUENCE_MAX_LENGTH - seqNo.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(seqNo);

        stringBuilder.append("00");
        return stringBuilder.toString();
    }

    private OrderDO convertFromOrderModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }
}
