package com.zwt.service.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class UserModel {
    private Integer id;

    @NotBlank(message = "请输入用户名")
    private String name;

    @NotNull(message = "请输入性别")
    private Integer sex;

    @NotNull(message = "请输入年龄")
    @Range(min = 0, max = 120, message = "年龄需要在0~120岁之间")
    private Integer age;

    @NotBlank(message = "请输入手机号")
    @Length(min = 11, max = 11, message = "请输入合法的手机号")
    private String telphone;

    private String registerMode;

    private String thirdPartyId;

    @NotBlank(message = "请输入密码")
    @Length(min = 6, max = 20, message = "请输入至少6位密码")
    private String encrptPassword;
}
