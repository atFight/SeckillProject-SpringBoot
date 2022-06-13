package com.zwt.controller;

import com.zwt.controller.viewobject.UserVO;
import com.zwt.error.BusinessException;
import com.zwt.error.EmBusinessError;
import com.zwt.response.CommonReturnType;
import com.zwt.service.UserService;
import com.zwt.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController extends  BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/getotp", method = {RequestMethod.POST, RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone")String telPhone) {
        //生成6位随机OTP验证码
        Random random = new Random();
        int randomCode = random.nextInt(999999) + 1000000;
        String otpCode = String.valueOf(randomCode).substring(1);

        //绑定手机号与验证码
        httpServletRequest.getSession().setAttribute(telPhone, otpCode);

        //模拟发送短信给客户进行验证
        System.out.println("telephone = " + telPhone + ", bind otp code = " + otpCode);
        return CommonReturnType.create(otpCode);
    }



    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);

        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
