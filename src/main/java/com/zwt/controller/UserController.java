package com.zwt.controller;

import com.alibaba.druid.util.StringUtils;
import com.zwt.viewobject.UserVO;
import com.zwt.utils.error.BusinessException;
import com.zwt.utils.error.EmBusinessError;
import com.zwt.utils.response.CommonReturnType;
import com.zwt.service.UserService;
import com.zwt.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",origins = "*", allowedHeaders = "*")
public class UserController{
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam("telphone")String telPhone,
                                  @RequestParam("password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(telPhone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        UserModel userModel = userService.validateLogin(telPhone, this.EncodeByMd5(password));
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/getotp", method = {RequestMethod.POST, RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone")String telPhone) {
        //??????6?????????OTP?????????
        Random random = new Random();
        int randomCode = random.nextInt(999999) + 1000000;
        String otpCode = String.valueOf(randomCode).substring(1);

        //???????????????????????????
        httpServletRequest.getSession().setAttribute(telPhone, otpCode);

        //???????????????????????????????????????
        System.out.println("telephone = " + telPhone + ", bind otp code = " + otpCode);
        return CommonReturnType.create(otpCode);
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam("telphone")String telPhone,
                                     @RequestParam("otpCode")String otpCode,
                                     @RequestParam("name")String name,
                                     @RequestParam("sex")Integer sex,
                                     @RequestParam("age")Integer age,
                                     @RequestParam("password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        String onSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(telPhone);
        if (!StringUtils.equals(otpCode, onSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "?????????????????????");
        }

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setSex(sex);
        userModel.setTelphone(telPhone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(this.EncodeByMd5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //??????????????????
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //???????????????
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
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
