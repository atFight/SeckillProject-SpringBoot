package com.zwt.service.model;

import lombok.Data;

@Data
public class UserModel {
    private Integer id;
    private String name;
    private Byte sex;
    private String age;
    private String telphone;
    private String registerMode;
    private String thirdPartyId;
    private String encrptPassword;
}
