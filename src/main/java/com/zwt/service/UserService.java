package com.zwt.service;

import com.zwt.utils.error.BusinessException;
import com.zwt.service.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;
    UserModel validateLogin(String telPhone, String encryptPassword) throws BusinessException;
}
