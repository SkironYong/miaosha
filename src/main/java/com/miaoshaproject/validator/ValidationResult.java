package com.miaoshaproject.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    //检验结果是否有错
    private boolean hasErrors =false;

    //存放错误信息的map
    private Map<String,String> errMsgMap = new HashMap<>();

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrMsgMap() {
        return errMsgMap;
    }

    public void setErrMsgMap(Map<String, String> errMsgMap) {
        this.errMsgMap = errMsgMap;
    }

    //实现通用的通过格式化字符串信息获取错误结果的msg
    public String getErrMsg(){
        //将全部错误信息以,分隔并返回
       return StringUtils.join(errMsgMap.values().toArray(),",");
    }
}
