package com.zwt.service;

import com.zwt.dataobject.OrderDO;
import com.zwt.error.BusinessException;
import com.zwt.service.model.OrderModel;

public interface OrderService {

    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
