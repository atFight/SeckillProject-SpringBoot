package com.zwt.response;

import lombok.Data;

@Data
public class CommonReturnType {
    private String status;
    private Object data;

    public static CommonReturnType create(Object data) {
        return CommonReturnType.create(data, "success");
    }

    public static CommonReturnType create(Object data, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setData(data);
        type.setStatus(status);
        return type;
    }
}
