package com.zwt.service;

import com.zwt.utils.error.BusinessException;
import com.zwt.service.model.OrderModel;

public interface OrderService {

    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
