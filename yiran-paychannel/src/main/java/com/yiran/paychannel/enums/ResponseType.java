package com.yiran.paychannel.enums;

public enum ResponseType {

    /**
     * 处理出错，如验签失败、返回数据有误等
     */
    ERROR,
    
    /**
     * 返回机构特定数据
     */
    RETURN_SERVER,
    
    /**
     * 跳转下个页面
     */
    FORWARD
    
}
