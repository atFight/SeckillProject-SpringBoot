package com.zwt.controller;

import com.zwt.error.BusinessException;
import com.zwt.error.EmBusinessError;
import com.zwt.response.CommonReturnType;
import com.zwt.service.OrderService;
import com.zwt.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",origins = "*", allowedHeaders = "*")
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/createorder", method = {RequestMethod.POST, RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam("itemId")Integer itemId,
                                        @RequestParam("amount")Integer amount) throws BusinessException {

        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        orderService.createOrder(userModel.getId(), itemId, amount);
        return CommonReturnType.create(null);
    }

}
