/**
 * 
 */
package com.yiran.member.request;

import java.util.List;


import com.yiran.member.base.Request;
import com.yiran.member.domain.BulkQueryItem;


/**
 * <p>查询会员账户状态请求</p>
 */
public class BulkAcctQueryRequest extends Request {

    /**
     * 
     */
    private static final long   serialVersionUID = 1083534468093702956L;

    private String              queryFlag;

    private List<BulkQueryItem> queryItems;

    public String getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(String queryFlag) {
        this.queryFlag = queryFlag;
    }

    public List<BulkQueryItem> getQueryItems() {
        return queryItems;
    }

    public void setQueryItems(List<BulkQueryItem> queryItems) {
        this.queryItems = queryItems;
    }

    @Override
    public String toString() {
        return "BulkAcctQueryRequest [queryFlag=" + queryFlag + ", queryItems.size="
               + (queryItems != null ? String.valueOf(queryItems.size()) : "0") + ", extention="
               + getExtention() + "]";
    }

}
