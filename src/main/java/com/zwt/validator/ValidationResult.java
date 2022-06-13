package com.zwt.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private boolean hasError = false;

    private Map<String, String> errorMsgMap = new HashMap<>();

    public String getAllErrorMsg() {
        return StringUtils.join(this.errorMsgMap.values().toArray(), ",");
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}
